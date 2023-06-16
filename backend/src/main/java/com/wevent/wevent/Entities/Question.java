package com.wevent.wevent.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuestion;
    String contenuQuestion;
    Boolean statutQuestion;
    Date dateCreationQuestion;

    @ManyToMany(mappedBy = "questions")
    Set<Utilisateur> utilisateurs = new HashSet<>();
    @OneToMany(mappedBy = "question")
    Set<ReponseQuestion> reponseQuestions = new HashSet<>();


}
