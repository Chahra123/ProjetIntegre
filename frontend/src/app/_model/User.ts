import { Role } from "./Role";

export class User{
  idUtilisateur!:number;
  prenom!:string;
  nom!:string;
  email!:string;
  motDePasse!:string;
  dateNaissance!:Date;
  numTel!:number;
  roles!:Role[];
}