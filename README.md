# Table of Contents

1. [HIBERNATE AND JPA](#hibernate-and-jpa)
2. [KEY TERMS](#key-terms)
    1. [JPA](#jpa)
    2. [ORM](#orm)
    3. [HIBERNATE](#hibernate)
    4. [JDBC](#jdbc)
    5. [SQL](#sql)
3. [THE HIBERNATE CONTEXT](#the-hibernate-context)
4. [CONNECTIVITY](#connectivity)
5. [SETUP](#setup)
6. [ANNOTATIONS](#annotations)
7. [OPERATIONS ON THE CONTEXT](#operations-on-the-context)
8. [ID GENERATION](#id-generation)
9. [PRIMARY KEY(PK)](#primary-keypk)

# HIBERNATE AND JPA

This is my full Hibernate journey documentation

## KEY TERMS

> ### JPA
> - JAKARTA PERSISTENCE API: This is just a specification

> ### ORM
> - OBJECT RELATIONAL MAPPING

> ### HIBERNATE
> - An implementation that satisfies JPA specifications

> ### JDBC
> - Java Database Connection

> ### SQL
> - STRUCTURED QUERY LANGUAGE

## THE HIBERNATE CONTEXT

> In ORMs a query is executed on the context. The context is the collection of
> instances of objects that can be controlled by the framework.
> ![context](https://thorben-janssen.com/wp-content/uploads/2020/07/Lifecycle-Model-1024x576.png)

> ### NOTE: ORMs WORK WITH CONTEXT INSTEAD OF DBMS DIRECTLY
> Every object in the context will be managed by the context.
> So any object you want to work with is placed into the context.
> The context will mirror the database to reflect.

## CONNECTIVITY

> I used two approaches to specify Hibernate's connection to a database:
> 1. Persistent XML file: resources > META-INF > persistence.xml
> 2. Java Configuration classes --> implements `PersistenceUnitInfo`

## SETUP

> - *Create an EntityManagerFactory*: A factory for creating EntityManagers.
    >   If you're using a persistence XML class then you use it for the factory.
> - *Create an EntityManager*: This is responsible for controlling the transactions and
    >   it represents the context.
> - *Describe the database*: Describe them as entity classes.
    >   By using @Entity, you mark a class as an entity, and it is mandatory to have an id marked
    >   with @Id.
> - You can now use the created EntityManager to operate on the entity.

### ANNOTATIONS

> `@Entity`: Its only purpose is to say that the class annotated with it is modeling
> an entity from the database.
> Giving a name to the entity is just to give a name to the object inside the context
> and not the database.

> `@Table`: This is how you modify the table in the database.

> `@Id`: This is mandatory and any entity is supposed to have an Id.
> A primary key can be:
> - A simple primary key
> - A composite primary key

> `@Column`: Enable details about the column.

> `Best practice is to use the annotations you only need`.

## OPERATIONS ON THE CONTEXT

> - `persist`: Adding an entity to the context.
> - `find`: Finding an entity from the context using the primary key.
> - `remove`: Marking an entity for removal.
> - `merge`: Merges an entity from outside the context into the context.
> - `refresh`: Mirror the context from the database: This is like an undo to any change to the entity
    >   in the context.
> - `detach`: Taking the entity out of the context.

> `find` vs `getReference`:
> The find executes a `select` query and if found the entity will be added to the context.
> But `getReference` doesn't execute a query to the database but a query will only be issued if you
> perform operations with the entity.

## ID GENERATION

> When creating an `ID` you can use automatic generation. This is by using the annotation
> `@GeneratedValue` which you can supply a `generator` or a `strategy`.

> **Note that not all generation strategies work with your DBMS.**

> `strategy`: This provides about 4 different existing strategies namely:
> - `Auto`: Rely on the default implementation *not recommended.
> - `Identity`: This allows the DBMS to generate the Id sequentially.
> - `Table`: This creates a second table where the identities are kept *worst in terms of performance.
> - `Sequence`: They generate values by a specific logic, mostly a sequence of values.
> - `UUID`: This is generated based on a UUID logic.

> **Choose strategy based on the DBMS you are using.**

## PRIMARY KEY (PK)

> You always have to find either one column or a combination of columns that are unique.
> For just one column it is named as a simple primary key and two or more columns
> is a composite primary key.

> **Generating your own strategy: Assuming I want to implement my own custom UUID generator (Simple Id)**:
> - Your class should implement `IdentifierGenerator` and the only abstract method you should
    >   override is `generate()`.
> - On your entity class, annotate the id
    >   with `@GenericGenerator(name = "UUIDGenerator", type = CustomUUIDGenerator.class)`
    >   and `@GeneratedValue(generator = "UUIDGenerator")`.

> **Composite Id: Creating IDs out of multiple values**
> 1. Options with ID class:
     > This option should always implement `Serializable` then annotate the entity class with
     > `@IdClass(YourComposite.class)` and then on the fields that you used for the combination,
     > annotate each with `@Id`.
> 2. Using Embeddable `@Embeddable`:
     > The representation will be an instance of an embeddable class then in your entity class
     > you no longer use `@Id` but rather `@EmbeddedId`.
