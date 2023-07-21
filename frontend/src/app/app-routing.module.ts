import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AppComponent } from './app.component';
import { SkillsComponent } from './skills/skills.component';
import { CurriculumVitaeComponent } from './curriculum-vitae/curriculum-vitae.component';
import { ProjectsComponent } from './projects/projects.component';
import { TranslateModule } from '@ngx-translate/core';
import { LoginComponent } from './login/login.component';
import { SocieteComponent } from './societe/societe.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { ForumComponent } from './forum/forum.component';
import { EventsComponent } from './events/events.component';
import { AjoutComponent } from './ajout/ajout.component';
import { PrecedentComponent } from './precedent/precedent.component';
import { DashComponent } from './dash/dash.component';
import { UserListComponent } from './user-list/user-list.component';
import { TokenFormComponent } from './token-form/token-form.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AddSocietyComponent } from './add-society/add-society.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UpdateSocietyComponent } from './update-society/update-society.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  { path: '', component:HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'dashboard', component: DashComponent },
  { path: 'login', component: LoginComponent },
  { path: 'skills', component: SkillsComponent },
  { path: 'ajout-reclamation', component: AjoutComponent },
  { path: 'precedent-reclamation', component: PrecedentComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'forum', component: ForumComponent },
  { path: 'events', component: EventsComponent },
  { path: 'societies', component: SocieteComponent },
  { path: 'societies/create', component: AddSocietyComponent, canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']}},
  { path: 'contact', component: ContactComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']} },
  { path: 'about', component: AboutComponent, canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']} },
  { path: 'users', component: UserListComponent, canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']} },
  { path: 'tokenform' , component: TokenFormComponent},
  { path: 'users/reset-password', component: ResetPasswordComponent},
  { path: 'users/create', component: CreateUserComponent , canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']} },
  { path: 'users/details/:id', component: UserDetailsComponent},
  { path: 'users/update/:id', component: UpdateUserComponent, canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']} },
  { path: 'societies/update/:id', component: UpdateSocietyComponent, canActivate:[AuthGuard],data:{roles:['ADMIN','ORGANISATEUR']} },

  { path: '**', component: ForbiddenComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    TranslateModule.forRoot(),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
