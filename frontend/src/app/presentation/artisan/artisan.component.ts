import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbDropdownModule, NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-artisan',
  standalone: true,
   imports: [
    CommonModule,
    NgbDropdownModule,
    NgbCollapseModule
  ],
  templateUrl: './artisan.component.html',
  styleUrl: './artisan.component.scss'
})
export class ArtisanComponent {
isMenuCollapsed = true;
}
