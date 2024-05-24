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
10. [ONE-TO-ONE RELATIONSHIP](#one-to-one-relationship)
11. [ONE-TO-MANY RELATIONSHIP](#one-to-many-relationship)
12. [MANY-TO-MANY RELATIONSHIP](#many-to-many-relationship)
13. [CASCADING](#cascading)
14. [FETCH](#fetch)
15. [ENTITY INHERITANCE](#entity-inheritance)

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
>
> [`@OneToOne`](#one-to-one-relationship) : Create a one-to-one relationship
>
> [`@JoinColumn`](#one-to-one-relationship) : Manipulate the foreignKey column on a table
>
> [`@OneToMany` and `@ManyToOne`](#one-to-many-relationship) : Indicates a one-to-many
>
> [`@JoinTable`](#many-to-many-relationship): This doesn't map to any table in the ORM but only
> used behind the scenes to create tables that manage the object

> `Best practice is to use the annotations you only need`.

## OPERATIONS ON THE CONTEXT

> - `persist`: Adding an entity to the context.
> - `find`: Finding an entity from the context using the primary key.
> - `remove`: Marking an entity for removal.
> - `merge`: Merges an entity from outside the context into the context.
> - `refresh`: Mirror the context from the database: This is like an undo to any change to the entity
    >   in the context.
> - `detach`: Taking the entity out of the context.
> - `flush` : Mirror it now, don't wait till end of the trasaction

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

## ONE-TO-ONE RELATIONSHIP

![img](https://vertabelo.com/blog/one-to-one-relationship-in-database/1-to-1-pk-plus-fk.png)
> This is a relationship where two entities A and B are linked in a way that exactly one instance
> of A is linked to one instance of B.
> `eg: A country can have only One capital city and a capital can belong to only country`
>
> To implement this relationship, there are two kinds of relationship in JPA implementation
> 1. Unidirectional --> only one entity know of the other.
> 2. Bidirectional --> Both entities knows about each other
     > To mark an entity as a 1-to-1,you anotate the field with `@OneToOne`
     > You can use `@JoinColumn` to manipulate the foreign key column
     > and for Bi-directional, the other end is going to recieved `mappedBy` attribute where
     > you have to specify the name of the field on the other end
     > **Whoever that have the `@JoinColumn` is the owning entity**
>
>  `orphanRemoval`: is a constraint that allows the child of remain in the DB even if the parent
> has been removed.
>
>

## ONE-TO-MANY RELATIONSHIP

> **Cas study** : Post and Comments
![img](https://www.callicoder.com/static/75ea6facc68feee16d4477ba58dd47d9/508ef/hibernate-one-to-many-mapping-example-table-structure.png)
>
> A post can have multiple comments but a comment belongs to exactly one post
>
> To specify a One-to-Many relationship there are 3 choices
> 1. A uni-directional relationship but from the comment side
> 2. A uni-directional relationship but from the post side
> 3. A bidirectional relationship
>
> There is `@ManyToOne` and `@OneToMany`
>
> - `@ManyToOne` -: Many comments to one post - And the annotated field should be a collection
> - `@OneToMany` -: One post to Many comments
>
> By default, with one-to-many relationship, a third table is created which holds the foreign keys
> and that table is called the joined table.
>
> Hence, is best to use the `@JoinColumn` to specify the name you want.
>
> **ESP** : The `JoinColumn` should always be on the opposite side .
>
> **In Many-to-One, the owning entity is always the side with `MANY`**
>
> In many-to-one the default fetch is eager because it is not a collection

## MANY-TO-MANY RELATIONSHIP

> **Cas study** : Users and Groups
>
> With this relationship, a user can belong to many groups whiles a group will have many
> users at the same time. This is a many-to-many relationship
>
> In many to many, you can not apply a physical relation and hence to implement this you
> need to use `@JoinTable`
>
> In this relationship there is no foreign key since it uses a joinTable. and you can chose
> between uni-directional or bidirectional relationship.
>
> `@ManyToMany` annotation is used to mark the relationship
>
> You then need to provide information about the joinTable
> ```angular2html
> @JoinTable(
> name = "user_groups",
> joinColumns = @JoinColumn(name = "group_id"),
> inverseJoinColumns = @JoinColumn(name = "user_id")
> )
> ```
> `inverseJoinColumns` this is for the opposite side of the relationship. In this case the user
>
> collections are with fetchType of Lazy by default
> This is done to avoid memory issues

## CASCADING

> When you apply an operation to a specific instance you want to apply the same to other
> instances that are linked to.
>
> Eg: If I persist a Country I want to automatically persist the Capital City
>
> Cascading is done by using the `cascade` attribute on the relationship annotation .
>
> There are multiple operations the cascade can perform, and they are all the operations we can do
> with the entity manager
>
> **Avoid using the` CascadeType.ALL`**

## FETCH

> default fetechType of any non-collection is by default `eager`. I.e. when you select a country
> automatically the capital city will be fetched as well.
>
> You can control this by using `FetchType.LAZY`

## ENTITY INHERITANCE

> This is not a concept you find very often but a great skill to have
> Assuming we have 2 different `Product` i.e. `Book` and `Electronics`.
>
> The Id can either be defined by the class or it's parent
>
> Because it is mandatory for an entity class to have a `PK`, in the case of inheritance,
> this `PK` can be put into a parent entity and all children entity will inherit that
>
> **How is Inheritance represented in DB?** : To do that you have to consider 3 strategies(Inheritance strategy)
> To chose the inheritance strategy, you need to annotate the parent class with `@Inheritance(strategy=type)`
> 1. `SINGLE_TABLE`: It will only define one table in the DB. This table will contain all the
     > children products. Using this strategy, Hibernate will create a `discriminator` column. This is added
  > to all attribute to specify the type of product. The query is made only to one table `Product`
> 2. `JOINED` : This will automatically create 3 table. And to get all that you need, You will
  > have to write queries using `JOIN`. 
> 3. `TABLE_PER_CLASS **ALWAYS AVOID**` : No longer supported by hibernate
>
> 