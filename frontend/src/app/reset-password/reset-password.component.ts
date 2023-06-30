import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { UserService } from './../_services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  token!: string;

  constructor(private userService:UserService,private route:ActivatedRoute,private router:Router) { }

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
      },
      (error) => {
        console.error('Error resetting password:', error);
        // Handle error or show an error message to the user
      }
    );
  }

}
