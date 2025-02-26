හයිබර්නේට් එකේ relationships (සම්බන්ධතා) තියෙන්නේ එක-to-එක (one-to-one), එක-to-බොහෝ (one-to-many), බොහෝ-to-බොහෝ (many-to-many) කියලා විදිහට. මේ එක එකේ **Annotations, MappedBy, හා Table වල structure** කොහොමද කියලා සිංහලෙන් පැහැදිලි කරන්නම්!

---

### 1. **එක-to-එක (One-to-One) Relationship**

- **ONE-TO-ONE nam mapBy eka kamathi paththaka puluwan senario eka anuwa**
- **උදාහරණය**: `User` හා `UserProfile` (එක user එකට profile එක).
- **Annotations**:
    - `@OneToOne` (දෙමුහුම් අතර සම්බන්ධතාවය දැක්වීමට).
    - `@JoinColumn` (Foreign Key column එක define කිරීමට).
- **MappedBy**: Child entity එකේ (`UserProfile`) `mappedBy` භාවිතා කරනවා.
- **Table Structure**:
    - `users` table එකේ `profile_id` කියන foreign key එකක් තිබෙනවා.
    - `user_profiles` table එකේ `user_id` කියන column එකට join වෙනවා.

```java
// Parent Entity (User)
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne(mappedBy = "user") // "user" යනු UserProfile එකේ field එක
    private UserProfile profile;
}

// Child Entity (UserProfile)
@Entity
public class UserProfile {
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id") // users table එකේ foreign key
    private User user;
}
```

---

### 2. **එක-to-බොහෝ (One-to-Many) Relationship**

- **ONE-TO-many nam mapBy eka ONE paththe danne**

- **උදාහරණය**: `Department` හා `Employee` (එක department එකට employees බොහෝ).
- **Annotations**:
    - Parent එකේ (`Department`) `@OneToMany`.
    - Child එකේ (`Employee`) `@ManyToOne` හා `@JoinColumn`.
- **MappedBy**: Parent එකේ `mappedBy` භාවිතා කරන්නේ child එකේ field එකට.
- **Table Structure**:
    - `employees` table එකේ `department_id` කියන foreign key එකක් තිබෙනවා.

```java
// Parent Entity (Department)
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToMany(mappedBy = "department") // Employee එකේ "department" field එක
    private List<Employee> employees;
}

// Child Entity (Employee)
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "department_id") // departments table එකේ foreign key
    private Department department;
}
```

---

### 3. **බොහෝ-to-බොහෝ (Many-to-Many) Relationship**

- **many-to-many nam mapBy eka kamathi paththaka paththe dann puluwan eka assocate table ekak hadenawa ,Habai !!! associate ekata thawa columns enawa nam ekata apita puluwan many to many to many eka relationship dekakta kada ganna ONE-TO-MANY aye Many-to-One widiyata ganna**
- **උදාහරණය**: `Student` හා `Course` (බොහෝ students බොහෝ courses ගන්නවා).
- **Annotations**:
    - දෙමුහුම් entity වලම `@ManyToMany`.
    - Owner side (`Student`) එකේ `@JoinTable` භාවිතා කරනවා.
    - Inverse side (`Course`) එකේ `mappedBy` භාවිතා කරනවා.
- **Table Structure**:
    - `students_courses` කියන **join table** එකක් හදනවා, එකේ `student_id` හා `course_id` columns දෙක තියෙනවා.

```java
// Owner Entity (Student)
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "students_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}

// Inverse Entity (Course)
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany(mappedBy = "courses") // Student එකේ "courses" field එක
    private List<Student> students;
}
```

---

### **මතක තියාගන්න ඕනේ:**
- **`mappedBy`** භාවිතා කරන්නේ **relationship එකේ inverse side** එකේ (එහෙමත් නැත්නම් child එකේ).
- **Foreign key column** එක හදන්නේ **child table** එකේ (උදා: `employees.department_id`).
- **Join table** හදන්නේ many-to-many relationships වලදී පමණයි.

---

ඔයාගේ ප්‍රශ්නයට සවිස්තරාත්මකව පිළිතුරු දෙන්නම්! **Hibernate Relationships, mappedBy, හා Ownership** කොහොමද වැඩ කරන්නේ කියලා පියවරෙන් පියවර පැහැදිලි කරනවා.

---

### **1. Relationship Ownership හා mappedBy යනු කුමක්ද?**
- **Relationship Ownership**: Hibernate එකේදී relationship එකක් **දෙපැත්තෙන්ම (bidirectional)** තිබුණත්, database එකේ **ඇත්තේ එක් foreign key එකක් හෝ join table එකක් පමණයි**.
    - එම foreign key/join table එක **manage කරන entity එකට** "**Owning Side**" කියනවා.
    - අනෙක් entity එක "**Inverse Side**" (හෝ "Mapped Side").
- **`mappedBy`**: Inverse Side එකේ භාවිතා කරන අනෝටේෂන් එකක්. එය **Owning Side එකේ ඇති field එකේ නම** දක්වනවා.

---

### **2. One-to-One (එක-to-එක) Relationship**
#### **උදාහරණය**: `User` (පියා) හා `UserProfile` (පුතා)
- **Owning Side**: `UserProfile` (Foreign Key එක `user_profiles` table එකේ ඇති නිසා).
- **Inverse Side**: `User` (මෙය `mappedBy` භාවිතා කරයි).

#### **Steps**:
1. **Owning Side (`UserProfile`)**:
    - `@OneToOne` දාන්න.
    - `@JoinColumn` එකෙන් foreign key column එක නම් කරන්න (`user_id`).
2. **Inverse Side (`User`)**:
    - `@OneToOne` දානවා.
    - `mappedBy = "user"` කියලා දෙන්න. ("user" යනු `UserProfile` එකේ ඇති `User` object එකේ නම).

```java
// Inverse Side (User)
@Entity
public class User {
    @Id
    private Long id;
    
    @OneToOne(mappedBy = "user") // UserProfile එකේ "user" field එක මගින් map කරලා
    private UserProfile profile;
}

// Owning Side (UserProfile)
@Entity
public class UserProfile {
    @Id
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id") // users table එකට join වෙන foreign key
    private User user;
}
```

#### **Database Tables**:
- `users` table: `id` (PK).
- `user_profiles` table: `id` (PK), `user_id` (FK to `users.id`).

---

### **3. One-to-Many (එක-to-බොහෝ) Relationship**
#### **උදාහරණය**: `Department` (පියා) හා `Employee` (පුතා)
- **Owning Side**: `Employee` (Foreign Key එක `employees` table එකේ ඇති නිසා).
- **Inverse Side**: `Department` (`mappedBy` භාවිතා කරයි).

#### **Steps**:
1. **Owning Side (`Employee`)**:
    - `@ManyToOne` දාන්න.
    - `@JoinColumn` එකෙන් foreign key column එක නම් කරන්න (`department_id`).
2. **Inverse Side (`Department`)**:
    - `@OneToMany` දාන්න.
    - `mappedBy = "department"` කියලා දෙන්න. ("department" යනු `Employee` එකේ ඇති field එක).

```java
// Inverse Side (Department)
@Entity
public class Department {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "department") // Employee එකේ "department" field එක මගින් map කරලා
    private List<Employee> employees;
}

// Owning Side (Employee)
@Entity
public class Employee {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "department_id") // departments.id ට join වෙන foreign key
    private Department department;
}
```

#### **Database Tables**:
- `departments` table: `id` (PK).
- `employees` table: `id` (PK), `department_id` (FK to `departments.id`).

---

### **4. Many-to-Many (බොහෝ-to-බොහෝ) Relationship**
#### **උදාහරණය**: `Student` හා `Course`
- **Owning Side**: `Student` (Join Table එක manage කරන්නේ `Student` එකේ ඇති `@JoinTable` නිසා).
- **Inverse Side**: `Course` (`mappedBy` භාවිතා කරයි).

#### **Steps**:
1. **Owning Side (`Student`)**:
    - `@ManyToMany` දාන්න.
    - `@JoinTable` එකෙන් join table එක නම් කරන්න (`students_courses`), හා columns දෙකම දෙන්න.
2. **Inverse Side (`Course`)**:
    - `@ManyToMany` දාන්න.
    - `mappedBy = "courses"` කියලා දෙන්න. ("courses" යනු `Student` එකේ ඇති `Course` ලැයිස්තුවේ නම).

```java
// Owning Side (Student)
@Entity
public class Student {
    @Id
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "students_courses",
        joinColumns = @JoinColumn(name = "student_id"), // Student එකේ PK
        inverseJoinColumns = @JoinColumn(name = "course_id") // Course එකේ PK
    )
    private List<Course> courses;
}

// Inverse Side (Course)
@Entity
public class Course {
    @Id
    private Long id;
    
    @ManyToMany(mappedBy = "courses") // Student එකේ "courses" field එක මගින් map කරලා
    private List<Student> students;
}
```

#### **Database Tables**:
- `students` table: `id` (PK).
- `courses` table: `id` (PK).
- `students_courses` join table: `student_id` (FK to `students.id`), `course_id` (FK to `courses.id`).

---

### **5. mappedBy භාවිතා කරන්නේ ඇයි? (මතක තියාගන්න ලොකු කාරණා)**
1. **Duplicate Updates වලක්වා ගැනීමට**: `mappedBy` නැත්නම් Hibernate එකට relationship එක **දෙපැත්තෙන්ම තියෙනවා කියලා හිතෙනවා**. එම නිසා join table හෝ foreign key දෙකක් හදනවා!
2. **Ownership පැහැදිලි කිරීමට**: කුමන entity එක foreign key/join table manage කරයිද කියලා දැනගන්න.
3. **Performance Optimization**: Hibernate එකට SQL queries generate කරන්න පහසු වෙනවා.

---

### **6. Common Mistakes හා Solutions**
1. **`mappedBy` නොදැමීම**:
    - **Error**: Hibernate එක join table දෙකක් හෝ foreign keys දෙකක් හදයි.
    - **Solution**: Inverse Side එකේ `mappedBy` දාන්න.

2. **අවුල් සහිත Cascade Types**:
    - **Error**: `CascadeType.ALL` දැමූ විට අනවශ්‍ය deletes/updates වෙයි.
    - **Solution**: Cascade types පරික්ෂා කරන්න (උදා: `CascadeType.PERSIST`).

3. **Lazy Loading Issues**:
    - **Error`: `FetchType.LAZY` භාවිතා කරද්දි `LazyInitializationException` එක්ක.
    - **Solution**: Transactions ඇතුලත relationships access කරන්න.

---

### **7. හැම Relationship එකකම Summary Table එකක්**

| Relationship Type  | Owning Side        | Inverse Side       | mappedBy යොදන තැන  | Database Structure                     |
|---------------------|--------------------|--------------------|---------------------|----------------------------------------|
| **One-to-One**      | Child Entity       | Parent Entity      | Parent Entity       | FK in Child Table                      |
| **One-to-Many**     | Many Side (Child)  | One Side (Parent)  | Parent Entity       | FK in Many Side Table                  |
| **Many-to-Many**    | Owner Entity       | Mapped Entity      | Mapped Entity       | Join Table with Both FKs               |

---

### **8. Key Points (මතක තියාගන්න)**
- **`mappedBy` භාවිතා කරන්නේ Inverse Side එකේ**.
- **Owning Side එක foreign key/join table එක තියෙන තැන**.
- **Java Code එකේදී දෙපැත්තෙන්ම Relationships සටහන් කරන්න**, නමුත් Database එකේ එක් අයිතමයක් පමණි.