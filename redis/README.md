## 数据结构

### 基本的数据结构 + 使用场景 + 特性
1. [简单动态字符串](doc/简单动态字符串.md)
2. [链表](doc/链表.md)
3. [字典](doc/字典.md)
4. [跳跃表](doc/跳跃表.md)
5. [整数集合](doc/整数集合.md)
6. [压缩列表](doc/压缩列表.md)

### redis对象
1. [字符串对象](doc/Redis对象.md#string)
2. [列表对象](doc/Redis对象.md#list)
3. [哈希对象](doc/Redis对象.md#hash)
4. [集合对象](doc/Redis对象.md#set)
5. [有序集合对象](doc/Redis对象.md#zset)

### 数据库
1. [服务端 + 客户端](doc/数据库.md#服务器+客户端)
2. [数据库键空间 + 读写键空间时的维护操作](doc/数据库.md#数据库键空间)
3. [过期间的判断 + 删除策略](doc/数据库.md#过期键)
4. [redis的过期键删除策略：惰性删除 + 定期删除](doc/数据库.md#redis的过期键删除策略)
5. [AOF、RDB和复制功能 对过期键的处理](doc/数据库.md#AOF、RDB和复制功能对过期键的处理)

### RDB持久化
1. [RDB文件的创建与载入](doc/RDB持久化.md#RDB文件的创建与载入)
2. [自动间隔性保存](doc/RDB持久化.md#自动间隔性保存)
3. [RDB文件结构](doc/RDB持久化.md#RDB文件结构)

### AOF持久化
1. [AOF持久化的实现](doc/AOF持久化.md#AOF持久化的实现)
2. [AOF文件的载入与数据还原](doc/AOF持久化.md#AOF文件的载入与数据还原)
3. [AOF重写](doc/AOF持久化.md#AOF重写)

