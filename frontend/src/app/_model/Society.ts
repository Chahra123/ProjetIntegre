import { Evenement } from "./Evenement";

export class Society
{
  idSociete!:number;
  nomSociete!:string;
  logoSociete!:Blob;
  numTel!:string;
  email!:string;
  addresse!:string;
  evenements!:Evenement[];
}
