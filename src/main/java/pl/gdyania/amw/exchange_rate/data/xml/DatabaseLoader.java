package pl.gdyania.amw.exchange_rate.data.xml;

import org.xml.sax.SAXException;
import pl.gdyania.amw.exchange_rate.data.domain.ExchangeRateModel;
import pl.gdyania.amw.exchange_rate.data.repository.ExchangeRateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class DatabaseLoader {
    private static final String HISTORY = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";
    private static final String CURRENCY = "currency";
    private static final String TIME = "time";
    private static final String RATE = "rate";

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public DatabaseLoader(ExchangeRateRepository exchangeRateRepository){
        this.exchangeRateRepository =exchangeRateRepository;
    }


    @Scheduled(cron ="0 15 16 * * MON-FRI")
    public void onSchedule() throws ParserConfigurationException, URISyntaxException, IOException, SAXException {
        download();
    }
    @PostConstruct
    public void download() throws ParserConfigurationException, URISyntaxException, IOException, SAXException {

        exchangeRateRepository.deleteAll();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(String.valueOf(new URI(HISTORY)));

        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("Cube");

        String fromCurrencyTxt = "EUR";
        String timeTxt = "time";

        for (int i = 1; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            Element element = (Element) node;
            if (element.hasAttribute(TIME)) {
                timeTxt = element.getAttribute(TIME);
            } else {
                String toCurrrencyTxt = element.getAttribute(CURRENCY);
                double rate = (Double.parseDouble(element.getAttribute(RATE)));

                ExchangeRateModel exchangeRateModel = new ExchangeRateModel(timeTxt, fromCurrencyTxt, toCurrrencyTxt, rate);
                exchangeRateRepository.save(exchangeRateModel);
            }
        }
    }
}
