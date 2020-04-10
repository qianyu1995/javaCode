#Hibernate

##2.1 ORM框架
 
Hibernate是一个数据持久化层的ORM框架.
Object：对象，java对象，此处特指JavaBean
Relational：关系，二维表，数据库中的表。
映射|映射元数据：对象中属性，与表的字段，存在对应关系。

save persist 区别
------------------------
presist 不保证立即执行,可能要等到flush;不更新缓存;没有返回值  
save 立即执行;返回主键;更新缓存   