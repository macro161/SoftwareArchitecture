package com.market.stocks.repository;

import com.market.stocks.model.Stock;
import com.market.stocks.model.User;
import com.market.stocks.validators.interfaces.IStockValidator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class DataLoader  implements CommandLineRunner{

    private IStockRepository stockRepository;
    private IStockValidator validator;
    private IUserRepository userRepository;

    Reader in = new FileReader("src/main/resources/datadump.csv");
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);


    @Autowired
    public DataLoader(IStockRepository stockRepository, IStockValidator validator, IUserRepository userRepository) throws IOException {
        super();
        this.stockRepository = stockRepository;
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (CSVRecord record : records) {
            Stock s1 = new Stock();
            s1.setStockName(record.get("Name"));
            s1.setPrice(Float.parseFloat(record.get("Price")));
            s1.setAvailableAmount(Float.parseFloat(record.get("Amount")));
            s1.setDescription(record.get("Description"));
            validator.executeValidations(s1);
            stockRepository.save(s1);
        }

        User u = new User();
        u.setName("Matas");
        u.setMoney(10000.0f);
        userRepository.save(u);
    }
}
