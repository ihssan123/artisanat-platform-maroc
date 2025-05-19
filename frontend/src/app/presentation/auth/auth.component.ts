import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../infrastructure/services/auth.service';
import { AuthRequest } from '../../infrastructure/dto/auth-request.dto';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../infrastructure/dto/register-request.dto';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
  ],
})
export class AuthComponent implements OnInit {
  loginForm!: FormGroup;
  registerForm!: FormGroup;
  isLoginMode = true;
  
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.initializeForms();
  }
  
  ngOnInit(): void {
    console.log('hi');
  }
  
  initializeForms(): void {
    // Formulaire de connexion
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      motDePasse: ['', Validators.required],
    });
    
    // Formulaire d'inscription
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      motDePasse: ['', Validators.required],
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
    });
  }

  onSubmit(): void {
    // Sélectionne le formulaire approprié selon le mode
    const currentForm = this.isLoginMode ? this.loginForm : this.registerForm;
    
    if (currentForm.valid) {
      const formValue = currentForm.value;
      
      if (this.isLoginMode) {
        // Mode connexion
        const authRequest: AuthRequest = {
          email: formValue.email,
          motDePasse: formValue.motDePasse,
        };
        
        console.log('Login request:', authRequest);
        this.authService.login(authRequest).subscribe({
          next: (response) => {
            console.log('Login response:', response);
            this.router.navigate(['/artisan/profil']);
          },
          error: (error) => {
            console.log('Login error:', error);
          },
        });
      } else {
        // Mode inscription
        const registerRequest: RegisterRequest = {
          email: formValue.email,
          motDePasse: formValue.motDePasse,
          nom: formValue.nom,
          prenom: formValue.prenom,
        };
        
        console.log('Register request:', registerRequest);
        this.authService.register(registerRequest).subscribe({
          next: (response) => {
            console.log('Register response:', response);
            this.router.navigate(['/artisan/profil']);
          },
          error: (error) => {
            console.log('Register error:', error);
          },
        });
      }
    } else {
      // Marquer tous les champs comme touchés pour afficher les erreurs
      Object.keys(currentForm.controls).forEach(key => {
        const control = currentForm.get(key);
        control?.markAsTouched();
      });
    }
  }
  
  toggleMode(): void {
    this.isLoginMode = !this.isLoginMode;
  }
}