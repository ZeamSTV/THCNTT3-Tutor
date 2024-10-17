package com.spring_boot.tutor.controller;

import com.spring_boot.tutor.model.Farm;
import com.spring_boot.tutor.repository.MySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmController {

    @Autowired
    private MySqlRepository mySqlRepository;

    @GetMapping("/farms")
    public List<Farm> getAllFarms() {
        return mySqlRepository.findAll();
    }

    @GetMapping("/farm/{id}")
    public Farm getFarmById(@PathVariable("id") Integer id) {
        return mySqlRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Farm addFarm(@RequestBody Map<String, String> body) {
        Farm farm = new Farm();
        farm.setName(body.get("name"));
        farm.setDescription(body.get("description"));
        farm.setImage(body.get("image"));
        farm.setTemperature(Float.parseFloat(body.get("temperature")));
        farm.setHumid(Float.parseFloat(body.get("humid")));
        mySqlRepository.save(farm);
        return farm;
    }

    @PutMapping("/update/{id}")
    public Farm updateFarm(@PathVariable("id") Integer id,
                           @RequestBody Map<String, String> body) {
        Farm farm = mySqlRepository.findById(id).orElse(null);
        if (farm != null) {
            farm.setName(body.get("name"));
            farm.setDescription(body.get("description"));
            farm.setImage(body.get("image"));
            farm.setTemperature(Float.parseFloat(body.get("temperature")));
            farm.setHumid(Float.parseFloat(body.get("humid")));
            mySqlRepository.save(farm);
        }
        return farm;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteFarmById(@PathVariable("id") Integer id) {
        if (mySqlRepository.existsById(id)) {
            mySqlRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
