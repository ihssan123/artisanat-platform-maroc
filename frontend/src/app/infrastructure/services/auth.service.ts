import { Injectable } from "@angular/core";
import { AuthServicePort } from "../../core/auth/auth-service.port";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { AuthRequest } from '../dto/auth-request.dto';
import { AuthResponse } from "../../core/models/auth-response.model";
import { Observable } from "rxjs";
import { RegisterRequest } from "../dto/register-request.dto";


@Injectable({ providedIn: 'root' })
export class AuthService implements AuthServicePort {
  private baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {}
    register(registerRequest: RegisterRequest): Observable<AuthResponse> {
          return this.http.post<AuthResponse>(`${this.baseUrl}/register`, registerRequest);
    }

  login(authRequest: AuthRequest): Observable<AuthResponse> {
    
    return this.http.post<AuthResponse>(`${this.baseUrl}/login`, authRequest);
  }
}
