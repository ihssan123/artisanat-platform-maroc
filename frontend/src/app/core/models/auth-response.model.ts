import { Role } from "./role.enum";

export interface AuthResponse {
    token: string;
    id:string;
    nom:string;
    prenom:string;
    email:string;
    motDePasse:string;
    telephone:string;
    adresse:string;
    role:Role;
  }
  