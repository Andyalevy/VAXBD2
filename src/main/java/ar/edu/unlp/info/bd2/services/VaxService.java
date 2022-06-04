package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.VaxException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Transactional
public interface VaxService extends VaxStatisticsService{



	/**
	 *
	 * @param email email del usuario con el cual ingresa al sitio
	 * @param fullname nombre y apellido del usuario
	 * @param password clave con la que el usuario ingresa al sitio
	 * @param dayOfBirth fecha de nacimiento del usuario
	 * @return el usuario creado
	 * @throws VaxException excepción al fallar
	 */
	Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException;

	/**
	 *
	 * @param name nombre de la vacuna
	 * @return la vacuna creada
	 * @throws VaxException excepción al fallar
	 */
	Vaccine createVaccine(String name) throws VaxException;

	/**
	 *
	 * @param patient paciente vacunado
	 * @param vaccine vacuna aplicada
	 * @param date fecha de aplicación
	 * @param centre el centro de vacunación donde se aplicó
	 * @param nurse enfermero/a que aplico la vacuna
	 * @return el shot creado
	 * @throws VaxException excepción al fallar
	 */
	Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException;
	

	/**
	 * 
	 * @param email email del usuario
	 * @return el paciente que tiene ese mail
	 */
	Optional<Patient> getPatientByEmail(String email);


	/**
	 *
	 * @param name nombre de la vacuna
	 * @return la vacuna con ese nombre
	 */
	Optional<Vaccine> getVaccineByName(String name);

	/**
	 *
	 * @param name nombre del centro de vacunación
	 * @return el centro de vacunación nuevo
	 * @throws VaxException excepción al fallar
	 */
	Centre createCentre(String name) throws VaxException;

	/**
	 * @param dni el dni
	 * @param fullName nombre del/la enfermero/a
	 * @param experience experiencia en años
	 * @return el enfermero creado
	 * @throws VaxException excepción al fallar
	 */
	Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException;

	/**
	* @param dni el dni
	* @param fullName nombre completo
	* @param area el area o areas de trabajo
	* @return el personal de apoyo creado
	* @throws VaxException
	* */
	SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException;

	/**
	 * @return el esquema nueva vacío
	 * @throws VaxException excepción al fallar
	 * */
	VaccinationSchedule createVaccinationSchedule() throws VaxException;

	/**
	 * @param id el id del esquema
	 * @return el esquema de vacunación
	 * */
	VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException;

	/**
	 * @param name el nombre del centro a buscar
	 * @return el centro
	 * */
	Optional<Centre> getCentreByName(String name) throws VaxException;

	/**
	 * @param staff el staff a actualizar
	 * @return el staff
	 * @throws VaxException excepción al fallar
	 */
	SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException;

	/**
	 * @param centre el centre a actualizar
	 * @return el centre
	 * @throws VaxException excepción al fallar.
	 */
	Centre updateCentre(Centre centre) throws VaxException;

	/**
	 * @param dni el dni del SupportStaff a buscar.
	 * @return el SupportStaff
	 * */
	Optional<SupportStaff> getSupportStaffByDni(String dni);

	/**
	 * @param vaccinationSchedule el vaccination schedule que se va a actualizar.
	 *
	 * @return el mismo vaccination schedule.
	 *
	 * @throws VaxException excepción al fallar.
	 */
	VaccinationSchedule updateVaccinationSchedule(VaccinationSchedule vaccinationSchedule) throws VaxException;
}
