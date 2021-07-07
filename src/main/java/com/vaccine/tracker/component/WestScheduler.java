/*
 * package com.vaccine.tracker.component;
 * 
 * import java.io.IOException; import java.time.LocalDateTime; import
 * java.time.format.DateTimeFormatter; import java.util.Arrays; import
 * java.util.List;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.MediaType; import
 * org.springframework.scheduling.annotation.Scheduled; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.vaccine.tracker.dto.CowinResponse; import
 * com.vaccine.tracker.dto.CowinResponseCentersContent; import
 * com.vaccine.tracker.dto.Session; import com.vaccine.tracker.dto.VaccineFees;
 * import com.vaccine.tracker.util.CentreUtil; import
 * com.vaccine.tracker.util.ToTelegram;
 * 
 * import lombok.Getter; import lombok.Setter;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @Component public class WestScheduler {
 * 
 * private static final Logger logger =
 * LoggerFactory.getLogger(Scheduler.class);
 * 
 * // @Value("${pincode2}") // String pincode; //
 * 
 * @Value("${baseUrl}") String baseUrl;
 * 
 * @Value("${msg}") String baseMsg;
 * 
 * static int counter = 0;
 * 
 * @Autowired protected RestTemplate restTemplate;
 * 
 * //@Scheduled(fixedDelay = 6000, initialDelay = 6000) public void
 * fixedDelaySch() { HttpHeaders headers = new HttpHeaders();
 * headers.set("User-Agent", "PostmanRuntime/7.26.1");
 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); HttpEntity
 * <String> entity = new HttpEntity<String>(headers); try { DateTimeFormatter
 * dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY"); String url =
 * baseUrl+"&date="+dtf.format(LocalDateTime.now());
 * logger.info("Ping count:"+(++counter)+" "+url+"\n"); CowinResponse response =
 * restTemplate.exchange(url, HttpMethod.GET, entity ,
 * CowinResponse.class).getBody(); if(response.getCenters().size()>0) {
 * processResponse(response); }else { CentreUtil.clearAllCentersPincodeTwo(); }
 * }catch(Exception e) { logger.error(e.getLocalizedMessage()); } }
 * 
 * private void processResponse(CowinResponse response) {
 * response.getCenters().forEach(cowinResponseCentersContent -> {
 * if(cowinResponseCentersContent!=null) {
 * if(!cowinResponseCentersContent.getSessions().isEmpty()) { try {
 * processFurther(cowinResponseCentersContent); } catch (IOException e) {
 * logger.error(e.getLocalizedMessage()); } } } });
 * 
 * }
 * 
 * private void processFurther(CowinResponseCentersContent
 * cowinResponseCentersContent) throws IOException{
 * cowinResponseCentersContent.getSessions().forEach(session -> {
 * if(session.getAvailable_capacity()>0) { Boolean send = false; int cost = 0;
 * String msg = ""; msg = checkForCost(cowinResponseCentersContent, session,
 * cost, msg); if(session.getMin_age_limit()<45 &&
 * CentreUtil.updatePinCodeTwo18Plus(cowinResponseCentersContent.getCenter_id()+
 * "-"+session.getDate(), cowinResponseCentersContent.getName())) { send = true;
 * } else if(session.getMin_age_limit()>=45 &&
 * CentreUtil.updatePinCodeTwo45Plus(cowinResponseCentersContent.getCenter_id()+
 * "-"+session.getDate(), cowinResponseCentersContent.getName())) { send= true;
 * } if(send) try { ToTelegram.send(msg); logger.info(msg); } catch (IOException
 * e) { logger.error(e.getLocalizedMessage()); } }else {
 * clearAndRenewCurrentSessionTracking(cowinResponseCentersContent,session); }
 * }); }
 * 
 * private String checkForCost(CowinResponseCentersContent
 * cowinResponseCentersContent, Session session, int cost, String msg) { msg =
 * String.format(baseMsg,
 * cowinResponseCentersContent.getPincode(),session.getDate(),
 * cowinResponseCentersContent.getName(),session.getAvailable_capacity(),session
 * .getAvailable_capacity_dose1(),session.getAvailable_capacity_dose2(),session.
 * getVaccine(),session.getMin_age_limit(),cowinResponseCentersContent.
 * getFee_type()); if(!cowinResponseCentersContent.getFee_type().equals("Free"))
 * { List<VaccineFees> list = cowinResponseCentersContent.getVaccine_fees();
 * for(VaccineFees s : list) { if(s.getVaccine().equals(session.getVaccine())) {
 * cost = s.getFee(); msg+="\nCost: Rs. "+cost; } } }
 * msg+="\n\nLink: https://selfregistration.cowin.gov.in/"; return msg; }
 * 
 * 
 * private void clearAndRenewCurrentSessionTracking(CowinResponseCentersContent
 * cowinResponseCentersContent, Session session) {
 * if(session.getMin_age_limit()<45) {
 * CentreUtil.getPincodeTwo18Plus().remove(cowinResponseCentersContent.
 * getCenter_id()+"-"+session.getDate()); }else {
 * CentreUtil.getPincodeTwo45Plus().remove(cowinResponseCentersContent.
 * getCenter_id()+"-"+session.getDate()); }
 * 
 * }
 * 
 * }
 */