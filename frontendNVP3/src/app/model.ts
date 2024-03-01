export interface LoginResponse {
  jwt: string;
}


export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  permissions: Permission[];
}

export interface Permission {
  id: number;
  name: string;
}


export interface VacuumCleaner {
  id: number;
  name: string;
  status: Status;
  createDate: Date;
  active: boolean;
  busy: boolean;
  used: number;
  version?: number;
  addedBy?: User;
}


export enum Status {
  START = 'ON',
  STOP = 'OFF',
  DISCHARGING = 'DISCHARGING'
}


export interface SearchRequest {
  name?: string;
  statusList?: string[]; // Assuming statusList is an array of strings
  dateFrom?: Date;
  dateTo?: Date;
}

export interface ErrorMessage {
  id: number;
  action: string;
  date: string;
  vacuumCleanerId: number;
  owner: number;
  message: string;
}
