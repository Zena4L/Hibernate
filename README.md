# HIBERNATE AND JPA

This is my full Hibernate journey documentation

## KEY TERMS

> ### JPA
>   - JAKARTA PERSISTENCE API : This is just a specification
> ### ORM
>     - OBJECT RELATIONAL MAPPING
> ### HIBERNATE
>    - An implementation that satisfy JPA specifications
> ### JDBC
>      - Java Database Connection
> ### SQL
>   - STRUCTURED QUERY LANGUAGE

## THE HIBERNATE CONTEXT

> In ORMs a query is executed on the context. The context is the collection of
> instances of objects that can be controlled by the framework
> ![context](https://thorben-janssen.com/wp-content/uploads/2020/07/Lifecycle-Model-1024x576.png)
> ### NOTE: ORMS WORK WITH CONTEXT INSTEAD OF DBMS DIRECTLY

## CONNECTIVITY

> I used to approach to specify hibernates connection to a database by using
>  1. Persistent XML file: resources > META-INF > persistence.xml
> 2. Java Configuration classes

 
## SETUP
> - *Create an EntityManagerFactory* : A factory for creating EntityManagers
>       If you're using a persistence xml class then you use it for the factory
> - *Create an EntityManager* : this is responsible for controlling the transactions and 
> it represent the context
> - *Describe the database* : Describe them as entity classes. 
>  By using @Entity, you mark a class as an entity, and it is mandatory to have an id marked
>  with @Id