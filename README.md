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
> Every object in the context will be managed by the context
> So any object you want to work with is placed into the context.
> The context will mirror the database to reflect

## CONNECTIVITY

> I used to approach to specify hibernates connection to a database by using
>  1. Persistent XML file: resources > META-INF > persistence.xml
> 2. Java Configuration classes --> implements `PersistenceUnitInfo`

## SETUP

> - *Create an EntityManagerFactory* : A factory for creating EntityManagers
    > If you're using a persistence xml class then you use it for the factory
> - *Create an EntityManager* : this is responsible for controlling the transactions and
    > it represent the context
> - *Describe the database* : Describe them as entity classes.
    > By using @Entity, you mark a class as an entity, and it is mandatory to have an id marked
    > with @Id
> - You can now use the created entity manager to operate on the entity.

### ANNOTATIONS

> `@Entity `: Is only purpose is to say that the class annotated with that is modeling
> an entity from the database.
> Giving a name to the entity is just to give name to the object inside the context
> and not the database
>
> `@Table` : This is how you modify the table in the database
>
> `@Id` : This is mandatory and any entity is supposed to have an Id.
> A primary key can be
>  - A simple primary key
>  - Compose primary key
     > `@Column` :  Enable details about the column.
>
>  `Best practise is to use the annatotions you only need`

## OPERATIONS ON THE CONTEXT

> - `persist` : adding entity to the context
> - `find` : finding entity from the context using the primary key.
> - `remove` :  marking entity from removal
> - `merge` : Merges and entity from outside the context into the context
> - `refresh` : Mirror the context from the database
> - `detach` : Taking the entity out of the context