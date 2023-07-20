import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog'
import { AddQuestionDialogComponent } from '../add-question-dialog/add-question-dialog.component';
@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {
  constructor(private matDialog: MatDialog){}
  questions: any[] = [
    {
      title: 'Question 1',
      content: 'This is the content of question 1.',
      createdAt: new Date(),
      user: {
        name: 'John Doe',
      },
      answers: [
        {
          content: 'Answer 1 to question 1.',
          top_answer :false,
          createdAt: new Date(),
          user: {
            name: 'Jane Smith',
          }
        },
        {
          content: 'Answer 2 to question 1.',
          top_answer :true,
          createdAt: new Date(),
          user: {
            name: 'Bob Johnson',
          }
        }
      ]
    },
    {
      title: 'Question 2',
      content: 'This is the content of question 2.',
      createdAt: new Date(),
      user: {
        name: 'Alice Brown',
      },
      answers: []
    }
  ];

  newAnswerContent: string = '';

  addAnswer(question: any) {
    const newAnswer = {
      content: this.newAnswerContent,
      createdAt: new Date(),
      user: {
        name: 'Your Name',
      }
    };
    question.answers.push(newAnswer);
    this.newAnswerContent = '';
  }
  openDialog(){
    this.matDialog.open(AddQuestionDialogComponent,{
      width : '350px',
    })
  }
}
