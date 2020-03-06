package es.eoi.cliente.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppService {

	static final String userID = "1";

	public void generaReporte(String reporte) {
		RestTemplate rt = new RestTemplate();
		if (reporte.equals("1"))
			rt.postForEntity("http://localhost:8080/reports/clientes/" + userID, null, String.class);
		if (reporte.equals("2"))
			rt.postForEntity("http://localhost:8080/reports/prestamos/" + userID, null, String.class);
	}
}
