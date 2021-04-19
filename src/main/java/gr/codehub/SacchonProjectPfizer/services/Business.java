package gr.codehub.SacchonProjectPfizer.services;

import gr.codehub.SacchonProjectPfizer.model.*;
import gr.codehub.SacchonProjectPfizer.repository.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class Business {



    public static void testMe(EntityManager em ) {
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        PatientRepository patientRepository = new PatientRepository(em);

        Consultation consultation = new Consultation();

        consultation.setDate(new Date());
        consultation.setConsult("All is ok");

        consultationRepository.save(consultation);

        Patient patient = new Patient();
        patient.setFullName("Gary Doe");
        patient.setUsername("Gary");
        patient.setPassword("1234");
      //  patient









        int consultationId = consultation.getId();
      // List<Consultation> consultations = consultationRepository.getConsultations(patient.getId());

       // List<Consultation> consultations = patientRepository.getConsultations();

    }





    public static void createData(EntityManager em) {


        PatientRepository patientRepository = new PatientRepository(em);


        Patient patient = new Patient();
        patient.setFullName("Evaggelia Barba");
        patient.setEmail("barba.evaggelia@gmail.com");
        patient.setPassword("1234");
        patient.setUsername("Lia");


        patientRepository.save(patient);
        System.out.println(patient);

        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = new Doctor();

        doctor.setFullName("Garyfallia Papadopoulou");
        doctor.setUsername("Filiw");
        doctor.setEmail("filiopapadopoulou@gmail.com");
        doctor.setPassword("4567");


        doctorRepository.save(doctor);
        System.out.println(doctor);

        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        ChiefDoctor chiefDoctor = new ChiefDoctor();

        chiefDoctor.setFullName("Anastasia Kyriakidou");
        chiefDoctor.setEmail("anastasiakyriakidou@gmail.com");
        chiefDoctor.setPassword("7890");
        chiefDoctor.setUsername("Sia8");


        chiefDoctorRepository.save(chiefDoctor);
        System.out.println(chiefDoctor);


        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = new Measurement();

        measurement.setDate(new Date());
        measurement.setTypeOfMeasurement("glucose");
        measurement.setValueOfMeasurement(2.5);
        measurement.setPatient(patient);


        measurementRepository.save(measurement);
        System.out.println(measurement);

        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = new Consultation();

        consultation.setConsult("Ola bainoun kalws");
        consultation.setDate(new Date());


        consultationRepository.save(consultation);
        System.out.println(consultation);


    }



}
