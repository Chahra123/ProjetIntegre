import { Society } from "./Society";

export class Evenement{

  idEvenement!:number;
  nomEvenement!:string;
  dateDebut!:Date;
  dateFin!:Date;
  descriptionEvenement!:string;
  prix!:number;
  nbrePersonnes!:number;
  image!:string;
  societe!:Society;
}
