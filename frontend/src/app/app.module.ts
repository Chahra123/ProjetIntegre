import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { CurriculumVitaeComponent } from './curriculum-vitae/curriculum-vitae.component';
import { ProjectsComponent } from './projects/projects.component';
import { SkillsComponent } from './skills/skills.component';
import { ContactComponent } from './contact/contact.component';
import { HeaderComponent } from './header/header.component';
import { ReferencesComponent } from './references/references.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomeComponent } from './home/home.component';
import { TranslateModule } from '@ngx-translate/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import { MatRadioModule } from '@angular/material/radio';
import { LoginComponent } from './login/login.component';
import { SocieteComponent } from './societe/societe.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReservationDialogComponent } from './reservation-dialog/reservation-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/Icon';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCardModule } from '@angular/material/card';
import { ReservationsComponent } from './reservations/reservations.component';
import { ForumComponent } from './forum/forum.component';
import { AddQuestionDialogComponent } from './add-question-dialog/add-question-dialog.component';
import { EventsComponent } from './events/events.component';
import { AjoutComponent } from './ajout/ajout.component';
import { PrecedentComponent } from './precedent/precedent.component';
import { DashComponent } from './dash/dash.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { LayoutModule } from '@angular/cdk/layout';
import { UserListComponent } from './user-list/user-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateUserComponent } from './create-user/create-user.component';
import { CreateSocietyComponent } from './create-society/create-society.component';
import { ProfileUserComponent } from './profile-user/profile-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    CurriculumVitaeComponent,
    ProjectsComponent,
    SkillsComponent,
    ContactComponent,
    HeaderComponent,
    ReferencesComponent,
    HomeComponent,
    LoginComponent,
    SocieteComponent,
    ReservationDialogComponent,
    ReservationsComponent,
    ForumComponent,
    AddQuestionDialogComponent,
    EventsComponent,
    AjoutComponent,
    PrecedentComponent,
    DashComponent,
    UserListComponent,
    CreateUserComponent,
    CreateSocietyComponent,
    ProfileUserComponent,
    ResetPasswordComponent,
    UserDetailsComponent
    ],
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    TranslateModule,
    BrowserAnimationsModule,
    MatRadioModule,
    MatMenuModule,
    MatDialogModule,
    MatCardModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatFormFieldModule,
    MatInputModule,
    TranslateModule.forRoot(),
    LayoutModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
