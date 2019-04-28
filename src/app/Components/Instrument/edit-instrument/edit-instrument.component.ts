import { Component, OnInit } from '@angular/core';
import { InstrumentService } from 'src/app/Services/instrument.service';

@Component({
  selector: 'app-edit-instrument',
  templateUrl: './edit-instrument.component.html',
  styleUrls: ['./edit-instrument.component.css']
})
export class EditInstrumentComponent implements OnInit {

  constructor(private instrumentService: InstrumentService) { }

  ngOnInit() {
  }

}
