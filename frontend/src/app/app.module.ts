import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { OrganisateurComponent } from './organisateur/organisateur.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/_auth/auth.guard';
import { AuthInterceptor } from 'src/app/_auth/auth.interceptor';
import { UserService } from './_services/user.service';
import { UserListComponent } from './user-list/user-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TokenFormComponent } from './token-form/token-form.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { NotfoundComponent } from './notfound/notfound.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    OrganisateurComponent,
    ForbiddenComponent,
    HeaderComponent,
    LoginComponent,
    UserListComponent,
    CreateUserComponent,
    UpdateUserComponent,
    UserDetailsComponent,
    DashboardComponent,
    TokenFormComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    NotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
