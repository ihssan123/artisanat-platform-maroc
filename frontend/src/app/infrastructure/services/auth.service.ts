import { Injectable } from "@angular/core";
import { AuthServicePort } from "../../core/auth/auth-service.port";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { AuthRequest } from '../dto/auth-request.dto';
import { AuthResponse } from "../../core/models/auth-response.model";
import { Observable } from "rxjs";
import { User } from "../../core/models/user.model";
import { RegisterRequest } from "../dto/register-request.dto";


@Injectable({ providedIn: 'root' })
export class AuthService implements AuthServicePort {
  private baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {}
    register(user: User): Observable<AuthResponse> {
        const dto: RegisterRequest = {
            nom:user.nom,
            prenom:user.prenom,
            email: user.email,
            motDePasse: user.motDePasse,
            telephone:user.telephone,
            adresse:user.adresse,
            role:user.role
          };
          return this.http.post<AuthResponse>(`${this.baseUrl}/register`, dto);
    }

  login(user: User): Observable<AuthResponse> {
    const dto: AuthRequest = {
      email: user.email,
      motDePasse: user.motDePasse
    };
    return this.http.post<AuthResponse>(`${this.baseUrl}/login`, dto);
  }
}
