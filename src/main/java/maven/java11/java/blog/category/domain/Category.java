package maven.java11.java.blog.category.domain;

import lombok.*;
import maven.java11.java.blog.base.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "CATEGORY")
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(
//                name = Category.NamedQuery_GetAllCategory,
//                procedureName = "SP_GET_ALL_CATEGORY",
//                resultClasses = Category.class,
//                parameters = {
//                    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR,type = void.class)
//                })
//})

public class Category extends BaseEntity {

    //public static final String NamedQuery_GetAllCategory = "getAllCategory";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
