# spboot-jpa

springboot JPA 整合hibernate

	1、 过程	
		Configuration - buildSessionFactory()
			SessionFactory - openSession()
				Session - save()
	2、 实现			
		JPA Java Prosistance API和Hiberante Extension
		
	3、 使用
		@Id 主键
		
	4、 常见的OR框架简介
		hibernate
		toplink
		jdo
		ibatis
		JPA 接口
		
	5、	Hibernate基础配置
	
		5.1、hibernate.hbm2ddl.auto: 自动创建|更新|验证数据库表结构
			create：
				每次加载hibernate时都会删除上一次的生成的表，
				然后根据你的model类再重新来生成新表，
				哪怕两次没有任何改变也要这样执行，
				这就是导致数据库表数据丢失的一个重要原因。
			create-drop ：
				每次加载hibernate时根据model类生成表，
				但是sessionFactory一关闭,表就自动删除。
			update：
				第一次加载hibernate时根据model类会自动建立起表的结构(前提是先建立好数据库)，
				以后加载hibernate时根据 model类自动更新表结构，
				即使表结构改变了但表中的行仍然存在不会删除以前的行。
				要注意的是当部署到服务器后，表结构是不会被马上建立起来的，
				是要等应用第一次运行起来后才会。
			validate ：
				每次加载hibernate时，
				验证创建数据库表结构，只会和数据库中的表进行比较，
				不会创建新表，但是会插入新值。
			
		先建表还是先建类
			一般的话需要先建表，因为如果是先建类的话，让Hibernate生成表
			则一些数据库表的优化工作就不能同时进行了
			
		搭建日志环境，显示ddl语句
			有什么日志实现
				slf4j, log4j, jdk logging api, apache commons-logging 
				
				整合slf4j、og4j: slf4j - slf4jlog4j - log4j
		
		
		注解内容
		如果属性和方法都可以用
		尽量在private成员变量上加注解
		
		5.2、打印SQL语句
		hibernate.show_sql = true	
		
		5.3、让打印的SQL更美观
		hibernate.format_sql = true 
		
		5.4、实体与数据库表的对应
		javax.persistence.Table
		@Table(name="_table") 
			
		5.5 实体属性与数据库表字段的对应
		javax.persistence.Column 
		@Column(name="_column") 
		
		5.6 不做持久化
		javax.persistence.Transient
		@Transient 
			
		5.7 映射枚举类型
		@Enumerated(EnumerType.STRING)
			EnumerType.STRING  使用字符串存储
			EnumerType.ORDINAL 使用数字存储，即枚举的下标
		
		
	6、 ID的生成策略 
			increment 
			identity 数据库内置标识字段提供支持
			sequence 数据库中使用序列
			uuid
			native 数据库自己选择需要什么策略
		-------------------------------------------------
			使用UUID
			@Id
			@GeneratedValue(generator="uuid")
			@GenericGenerator(name="uuid",strategy="uuid")
		-------------------------------------------------
			自增主键
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private Integer id;
		---------------------------------------------------
		@GeneratedValue,默认使用AUTO
		只修改字段类型，hibernate不会重新生成表
		
		GenerationType
			AUTO
			IDENTITY
			TABLE 
			SEQUENCE
		
		------------------------------------------------------------------
		ORACLE不支持自增主键，使用SEQUENCE
			@Id
			@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="entitySEQ")
			
			在实体上上注解,会对每一个实体对应的表，生成一个sequence,否则共用一个sequence
			@SequenceGenerator(name="entitySEQ", sequenceName="entity_SEQ_DB")
		----------------------------------------------------------------------
		TABLE 使用于任何数据库
		
			属性上注解
				@Id
				@GeneratedValue(strategy=GenerationType.TABLE, generator="entiry_GEN")
			
			实体上注解@TableGenerator, javax.persistence.TableGenerator
				@TableGenerator(
					name="entiry_GEN",
					table="GENERATOR_TABLE",
					pkColumnName="pk_key", //注意 key是MySQL中的关键字
					valueColumnName="pk_value",
					pkColumnValue="Teacher",
					allocationSize=1
				)
				
		    数据库中对应生成的表 GENERATOR_TABLE
			------------------------------------
				key		 |    value 
			------------------------------------
				Teacher  |     1 
			------------------------------------
			如实体teacher对应的操作，取完1后，value就变成了2 
			
		-------------------------------------------------------------------		
				
		联合主键
			
			hibernate执行后会有创建表的语句出现: primary key(id,name)
			
			创建主键类 EntityPK{id,name},实体类持有EntityPK的引用
			private EntityPK pk;
			
			三种不同的方法构建联合主键
			1、
				@Embeddable
				EntityPK{id,name}
				
				Entity{	
					@Id
					private EntityPK pk;
				}
			
			2、
				Entity{	
					@EmbeddedId
					private EntityPK pk;
				}
				
				
			3、 
				EntityPK不需要加任何注解
				@IdClass(value=EntityPK.class)
				Entity{	
					@Id
					id;
					
					@Id
					name;
				}

		)
		
	7、 核心开发接口
			Configuration 默认读取 hibernate.cfg.xml
				AnnotationCofiguration 
			SessionFactory currentSession() -> Session 
								可能新建，也可能取原有的，Session一旦commit,自动关闭，再拿就是新的Session了
				           openSession() 新建Session，需要手动close
						   
			currentSession需要设置Session上下文		
			current-session_context_class: jta,thread
				thread 使用一个数据库连接
				
			分布式事务JTA(Java Transaction API)
				跨越了多个事务管理源(比如两个或者是多个不同的数据源)，
				你就需要使用JtaTransactionManager	
			
		Hibernate中对象的三种状态
			Transient  new,新创建;内存中有，没ID,缓存没有
			Persistent 调用save(),就有id了;内存中有，ID,缓存有，数据库有
			Detached   Session close();内存中有，ID,缓存没有，数据库有
			
			
		Session的方法
			save()
			delete() - >转换成Transient状态	
			
			load(Class,Serializable) //类型，主键	
			get(Class,Serializable)  //类型，主键	
			load和get的区别
				get查询SQL后，装到指定的对象
				load是通过代理，当拿对象的属性时，才执行SQL
			
			update
				更新detached对象 
				更新Transient对象会报错，更新设定好的有id的可以(前提是数据库里有)
				Persistent的对象，只要修改了任一属性，就会update
				但是当跟原有的值相同，不会发送update语句 (默认全部字段更新)
				
			    不想跟着其他字段一起更新
					@Column(updatable=false)
				
			saveOrUpdate()//执行save或者update
			clear() 	//清理缓存
			
			flush()	强制缓存的内容和数据库的内容做同步
				commit 时默认flush
				session.setFlushMode(FlushMode.AUTO,COMMIT ALWAYS...)
				
				
	8、 关系映射 
			对象之间的关系 (一对一 | 一对多 | 多对多)(单向 | 双向)
				一对一单向外键关联
					Husband{
						@OneToOne
						@JoinColumn(name="pk_wife") //指定外键的名称
						private Wife wife
					}
					
					hibernate会在数据库中husband对应的表中生成一个wife外键
					
					对应XML配置(以学生和学生证的关系举例，学生证中有Student属性) 
					<hibernate-mapping>
						<class name="com.ethan.StudentId">
							<many-to-one name="student" column="studentId" unique="true"></many-to-one>
						</class>
					</hibernate-mapping>
				
				一对一双向外键关联 
					@OneToOne(mapppedBy="")
					用于只生成一个关联，mappedBy的值是在另一方的属性的名称
					如Husband中的Wife wife属性，此时mapppedBy="wife"
					凡是双向关联，必设mappedBy 
					
					XML配置：
					<hibernate-mapping>
						<class name="com.ethan.Student">
						<id name="id">
							<generator class="native"/>
						</id>
						<property name="name"/>
							<one-to-one name="studentIdCard" peoperty-ref="student"/>
						</class>
					</hibernate-mapping>
					
				一对一单向主键(id-id)关联 -  不重要
					hibernate自定生成外键约束(id-id)
					<hibernate-mapping>
						<class name="com.ethan.StudentId">
							<id name="id">
								<generator class="foreign">
									<param name="property">student</param>
								</generator>
							</id>
							<one-to-one name="student" constrained="true"/>
						</class>
					</hibernate-mapping>
					
				一对一双向主键(id-id)关联 -  不重要			
				Wife{
				    private Husband husband;
					
					@OneToOne
					@PrimaryKeyJoinColumn
					public Husband getHusband(){
						return husband;
					}
				}
				Husband类同理

				XML配置：
				<hibernate-mapping>
					<class name="com.ethan.Student">
						<id name="id">
							<generator class="native"/>
						</id>
						<property name="name"/>
						<one-to-one name="studentIdCard" peoperty-ref="student"/>
					</class>
				</hibernate-mapping>	
				
				联合主键
				
				@Entity
				@IdClass(value=WifePK.class)
				public class Wife1 {
					
					@Id
					@GeneratedValue(generator="uuid")
					@GenericGenerator(name="uuid",strategy="uuid")
					public String getId() {
						return id;
					}
					
					@Id
					@Column(name = "name")
					public String getName() {
						return name;
					}
				}
				主键类
				public class WifePK implements Serializable{
					
					private static final long serialVersionUID = 8874686468521454489L;
					private String id;
					private String name;
					
				Husband1{
					@OneToOne
					@JoinColumns(value={
							@JoinColumn(name="pk_wife1_id",referencedColumnName="id"),
							@JoinColumn(name="pk_wife1_name",referencedColumnName="name")
					})
					public Wife1 getWife1() {
						return wife1;
					}	
				} 		

				组件映射 
					A -> B
					注解
					在A的B组件上注解@Embedded
					
					XML的实现
					<hibernate-mapping>
						<class="com.Husband">
							<id name="id">
								<generator class="vaive"/>
							</id>
							<property name="name"/>
						
							<component name="wife">
								<property name="wifeName"/>
								<property name="age"/>
							</component>
						</class>
					</hibernate-mapping>	
				
			多对一与一对多
			
			多对一
				注解 
				在多类的get方法上注解 @ManyToOne
				
				XML配置：
				<hibernate-mapping>
					<class="com.User" table="t_user">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<many-to-one name="group" column="groupId">
						</many-to-one>
					</class>
				</hibernate-mapping>
			
			一对多
				<hibernate-mapping>
					<class="com.Group" table="t_group">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<set name="users">
							<key column="groupId"/> //关联的名称
							<one-to-many class="com.User">
							</one-to-many>
						</set>
					</class>
				</hibernate-mapping>
			
			一对多，多对一双向
				注解
				Person：
				//凡是双向需要加mappedBy，避免生成两个重复外键
				@OneToMany(mappedBy="person")
				public Set<BankCard> getCards() {
					return cards;
				}
			
				BankCard：
				@ManyToOne
				public Person getPerson() {
					return person;
				}
				
				XML配置 
				<hibernate-mapping>
					<class="com.Group" table="t_group">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<set name="users">
							<key column="groupId"/> //关联的名称
							<one-to-many class="com.User">
							</one-to-many>
						</set>
					</class>
				</hibernate-mapping>
				
				<hibernate-mapping>
					<class="com.User" table="t_user">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<many-to-one name="group" column="groupId"></many-to-one>
					</class>
				</hibernate-mapping>
				
		    多对多
				A - 关系表 - B
				
				多对多单向关联 
				@ManyToMany
				@JoinTable(//指定关系表中的字段的名称
					name="t_provider_customer",
					joinColumns=@JoinColumn(name="provider_id", 
											referencedColumnName="id"),
					inverseJoinColumns=@JoinColumn(name="customer_ID", 
											referencedColumnName="id")	
				)
				public Set<Customer> getCustomers() {
					return customers;
				}

				XML配置 
				<hibernate-mapping>
					<class="com.Teacher" table="t_teacher">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<set name="students" table="teacher_student">
							<key column="teacherId"/> //关联的名称
							<many-to-many class="com.Student" column="studentId">
							</many-to-many>
						</set>
					</class>
				</hibernate-mapping>
				
				<hibernate-mapping>
					<class="com.Student" table="t_student">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
					</class>
				</hibernate-mapping>
			------------------------------------------------------------------
			多对多双向关联 
				
			@ManyToMany(mappedBy="customers")//配置另一个类的属性名
			public Set<Provider1> getProvider1s() {
				return provider1s;
			}
			
			@ManyToMany
			@JoinTable(
				name="t_provider1_customer1",
				joinColumns=@JoinColumn(name="provider_id", 
										referencedColumnName="id"),
				inverseJoinColumns=@JoinColumn(name="customer_id", 
										referencedColumnName="id")	
			)
			public Set<Customer1> getCustomers() {
				return customers;
			}

			XML配置 
				<hibernate-mapping>
					<class="com.Teacher" table="t_teacher">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<set name="students" table="teacher_student">
							<key column="teacherId"/> //关联的名称
							<many-to-many class="com.Student" column="studentId">
							</many-to-many>
						</set>
					</class>
				</hibernate-mapping>
				
				<hibernate-mapping>
					<class="com.Student" table="t_student">
						<id name="id">
							<generator class="vaive"/>
						</id>
						<property name="name"/>
						<set name="teachers" table="teacher_student">
							<key column="studentId"/> //关联的名称
							<many-to-many class="com.Teacher" column="teacherId"> //对方的外键
							</many-to-many>
						</set>
					</class>
				</hibernate-mapping>	
				
		---------------------------------------------------------------------
		9、 CRUD 
				//级联，调用与枚举同名方法都会级联，如merge(u);
				@ManyToOne(cascade={CascadeType.ALL,MERGE,PERSIST,REFRESH,REMOVE})
				@JoinColumn(name="groupId")//自身外键的名称
				public Group getGroup() {
					return group;
				}
			双向关系要设定双向关联，设定mappedBy 
			@ManyToOne默认是级联的，都会把多对一的一那一方取出
			
			json处理双向关联的死循环问题未解决？
 
			@OneToMany(mappedBy="group",
				cascade={CascadeType.All},
				fetch=FetchType.LAZY[,EAGER]
			)
			@OneToMany fetch负责读，默认为LAZY
			
			LAZY是在用到的时候才会查SQL ,并拿到内存中 
			@ManyToOne默认EAGER
				 
			UPDATE 
				更新detached对象
				
				
			DELETE
				如果设置了级联，会级联删除
				如果设置了级联，且不想级联删除，可以打破其关联关系，load出对象来，将对应的属性设置为空
			
			    或者 createQuery("delete from User u where uid=1").executeUpdate();
			
			
		10、集合映射 
			
		    List和Set的使用方式相同
			
			Group：
			
			@OneToMany(
				mappedBy="group",
				cascade={CascadeType.ALL}
			)
			@OrderBy//默认按照主键排序，或者@OrderBy("name ASC")
			public List<User> getUsers(){
				return users;
			}
			
			Map的映射
			
			private Map<Integer,User> users=new HashMap<Integer,User>();
			
			@OneToMany(mappedBy="group",cascade={CascadeType.ALL})
			@MapKey(name="id")
			getUsers()
			
			继承映射 
				处理继承的表创建方式：
					一张大表 SINGLE_TABLE
					每个类分别一张表 TABLE_PER_CLASS
					每个子类一张表，用id主键关联，JOINED
			
			Hibernate查询 (QL)
				可以使用的查询语言
					NativeSQL,如Mysql,oracle,postgresql
					HQL,hibernate的查询语言，转换成具体的方言
					EJB(JP QL),HQL的一个子集
					QBC QUERY BY CRITERiA
					QBE QUERY BY EXAMPLE
					
			Query q = session.createQuery("from Category");
			List<Category> lsit =(List<Category> )q.list();
					
					
			("from Category c where c.name > 'c5'");
			
			("from Category c order by c.name desc");
					
			("select distinct c from Category c order by c.name desc");	
			
			占位符的使用
				("from Category c where c.id > :min and c.id < :max");
				 q.setParameter("min",2);
				 q.setParameter("max",8)
			
			分页的使用
				("from Category c order by c.name desc");
				q.setMaxResults(4); //4条
				q.setFirstResult(2);//从第二条开始
			
			
			("select c.id,c.name from Category c order by c.id desc");
			List<Object[]> re = (List<Object[]>)q.list();
			每个对象对应的id,name放在一个Object[]中
			
			("from Topic t where t.category.id = 1"); //以对象的角度来查
			
			("from Msg m where m.topic.category.id = 1");//板块为1的所有的帖子
			
			("select new com.MsgInfo(m.id,m.cont,m.topic.title.m.topic.category.name) from Msg")
			
			("select t.title,c.name from Topic t join t.category c")
			
			("from Msg m where m = MsgToSearch");//对象比较
			q.setParameter("MsgToSearch",m1);
			
			
			("from count(*) from Msg m")
			
			("from Msg m where m.id between 3 and 5")
			
			("from Msg m where m.id in(3,4,5)")
			
			("from Msg m where m.count is not null")
			
			("from Topic t where t.title like '%5'")
			("from Topic t where t.createDate < :date")
			
			("select current_date,current_time,current_timestamp from Topic t")
			
			("from Topic t where t.title like '_5'")
			q.setParameter("date",new Date());
			q.list();
			
			("select t.title,count(*) from Topic t group by t.title having count(*)>1")
			
			("from Topic t where t.id <(select avg(t.id) from Topic t)")
			
			("from Topic t where t.id < All(select t.id from Topic t where mod(t.id,2)=0))")//小于最小值
			
			("from Topic t where not exists(select m.id from Msg m where m.topic.id=t.id)")
			
		11、性能优化	
			尤其遇到分页的时候
			session.clear()清理缓存
			
			Java有内存泄露吗
				语法层面上没有，由于虚拟机的垃圾回收
				但是在本地的代码中
				当开启了资源需要关闭
				
			1+N问题解决：
				1、ManyToOne,默认为EAGER，由于关联关系，多执行的SQL语句
						FetchType设为LAZY，什么时候用，则什么时候发出N条SQL语句
				2、在ManyToOne的Many实体上注解
					@BatchSize(size=[数目X])// 生成的SQL会用in语句，一次性的取X个
					
				3、[left join fetch] ("from Topic t left join fetch t.category c")
				
				4、iterator
					list 直接取出，每次都查询数据库，不会读缓存
					iterator 只取id，用到对象再发SQL语句查，放到缓存中，
							 再使用时会利用session中的缓存
					
					
				5、 一级缓存二级缓存查询缓存
				
					一级缓存
						Session级别的缓存，一个Session不能与其他Session公用
					二级缓存
						公共的缓存，SessionFactory级别的缓存，可以跨Session
							支持Hashtable(不建议用),
							EHCache,硬盘和内存
							OSCache,硬盘和内存
							SwarmCache 集群可用
							JBossCache,
						
						EHCache的使用 
							cache.use_sencond_level_cache 设置为true
							cache.provier_class 设置org.hibernate.cache.EhCacheProvider
							
							类注解
								@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,
								[NONE
								READ_ONLY
								TRASACTIONAL
								READ_WRITE//既能读又能改]
								
								region="自定义的cache策略"
								
								)
							
						适合放到二级缓存中的
							数据量不大
							经常被访问的
							改动小的
							
						load默认使用二级缓存，iterator默认使用二级缓存 
						list()默认往二级缓存里放，但是查询的时候不使用
						
						查询缓存
							两个查询一样
							cache.use_query_cache 设置为true 
							调用Query 的setCacheable(true)指明使用二级缓存
							
						缓存算法
							LRU：Least Recently Used 最近很少使用
							LFU: Last Frequently Used 命中率高低
							FIFO: First In First Out 先进先出
							
				6、 事务隔离机制
					
					什么是一个事务
						要么都完成，要么都不完成
						
					ACID
						Atomic 	原子性是指事务是一个不可分割的工作单位，
								事务中的操作要么都发生，要么都不发生。
						一致性（Consistency）
						Consistency
							事务前后数据的完整性必须保持一致。
							逻辑的完整，A扣钱，B收钱
						Isolation
							事务的隔离性是多个用户并发访问数据库时，
							数据库为每一个用户开启的事务，
							不能被其他事务的操作数据所干扰，
							多个并发事务之间要相互隔离。
						Durability 持久性
							持久性是指一个事务一旦被提交，
							它对数据库中数据的改变就是永久性的，
							接下来即使数据库发生故障也不应该对其有任何影响
						
						事务的并发问题
							脏读：读到了其他事务的数据
							不可重复读：重复读前后不一致
							幻读：读到其他事务插入和删除的操作，多了新记录出来
						
						事务隔离级别
							TRANSACTION_NONE
							TRASACTION_READ_UNCOMMITED 读未提交，允许脏读等等，
							TRANSACTION_READ_COMMITED  不可重复读，不会出现脏读，锁住行
							TREANSACTION_REPEATABLE_READ 可重复读，读出来加锁，锁索引，没有索引，锁表
							TRANSACTION_SERIALIZABLE 串行化，锁表
							
						MYSQL	
							select @@tx_isolation; 默认REPEATABLE_READ
							
						悲观锁和乐观锁	
							悲观锁：读出加锁 select xxx for update
								load(Account,1,LockMode,UPGRADE)//load时指定加锁的方式,数据库加锁
							
							乐观锁
								在程序中加锁
									设置字段，如version,一旦有人更新，则+1
									检查拿出的version和重拿的值是否一致
									
								乐观锁的实现
									//数据库中生成version列，当不同的session更改时
									//提交会出现问题
									@Version
									public int getVersion(){
										return version
									}
							
			12、总结 
			
				Hibernate是开放源代码的对象关系映射框架
					
					get
					load 
					Session
					ID生成策略
					对象状态：Transient,Persistent,Detached 
					对象关联关系：
						一对一(主外键)@OneToOne
						一对多 @OneToMany
						多对一 @ManyToOne()
						多对多 @ManyToMany
							双向关联mappedBy，凡是双向关联，必设mappedBy 
					集合映射 
					
					HQL
					NativeQL
					
					N+1 FetchType.ERGET/LAZY 
					
					缓存 
						最少用
						命中率最低 
						FIFO
					事务 
							解决NONREPEATABLE - 乐观锁，悲观锁
							
				
			
			
