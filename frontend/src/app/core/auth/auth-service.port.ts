import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { AuthResponse } from '../models/auth-response.model';

export interface AuthServicePort {
  login(user: User): Observable<AuthResponse>;
  register(user: User): Observable<AuthResponse>;
}
