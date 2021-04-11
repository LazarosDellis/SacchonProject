package gr.codehub.SacchonProjectPfizer.services;

import gr.codehub.SacchonProjectPfizer.model.*;
import gr.codehub.SacchonProjectPfizer.repository.*;

import javax.persistence.EntityManager;
import java.util.Date;

public class Business {
    public static void createData(EntityManager em) {

        PatientRepository patientRepository = new PatientRepository(em);
//         Patient patient1 = new Patient();
//
//        patient1.setFullName("Lazaros Dellis");
//        patient1.setEmail("lazarosdellis@gmail.com");
//        patient1.setPassword("1234");
//        patient1.setUsername("Larry");
//
//        patientRepository.save(patient1);
//        System.out.println(patient1);

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

    public static void testMe(EntityManager em) {

    }
}
