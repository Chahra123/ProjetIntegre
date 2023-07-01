import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { UserService } from './../_services/user.service';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  token!: string;
  @ViewChild('content')
  modalContent!: TemplateRef<any>;
  constructor(private userService:UserService,private route:ActivatedRoute,private router:Router,private modalService:NgbModal) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
    });
  }

  resetPassword(resetForm: NgForm) {
    const password = resetForm.value.password;

    this.userService.resetPassword(this.token, password).subscribe(
      (response: any) => {
        console.log('Password reset successful:', response);
        // Handle success or show a notification to the user
        this.router.navigate(['/login']);
        this.openModalPwd();
      },
      (error) => {
        console.error('Error resetting password:', error);
        // Handle error or show an error message to the user
      }
    );
  }
  openModalPwd() {
    this.modalService.open(this.modalContent, { centered: true });
  }

}
