import { Role } from "../../core/models/role.enum";

export interface RegisterRequest{
        nom:string;
        prenom:string;
        email:string;
        motDePasse:string;
        telephone:string;
        adresse:string;
        role:Role;
      
}