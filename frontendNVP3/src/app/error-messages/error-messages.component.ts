import { Component } from '@angular/core';
import {ErrorMessage} from "../model";
import {ErrorMessagesService} from "../services/error-messages.service";

@Component({
  selector: 'app-error-messages',
  templateUrl: './error-messages.component.html',
  styleUrls: ['./error-messages.component.css']
})
export class ErrorMessagesComponent {
  errorMessages: ErrorMessage[] = [];

  constructor(private errorMessageService: ErrorMessagesService) {}

  ngOnInit(): void {
    this.loadErrorMessages();
  }

  loadErrorMessages(): void {
    this.errorMessageService.findAllErrorMessagesForUser().subscribe(
      (data: ErrorMessage[]) => {
        this.errorMessages = data;
      },
      (error: any) => {
        console.error('An error occurred while fetching error messages:', error);
      }
    );
  }
}
