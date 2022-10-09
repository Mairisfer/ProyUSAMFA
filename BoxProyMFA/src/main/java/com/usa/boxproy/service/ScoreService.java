package com.usa.boxproy.service;


import com.usa.boxproy.entities.Score;
import com.usa.boxproy.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }
    public Optional<Score> getScore(int idScore){
        return scoreRepository.getScore(idScore);
    }
    public Score save(Score sc){
        if (sc.getIdScore()==null){
            return scoreRepository.save(sc);
        }else{
            Optional<Score> sr = scoreRepository.getScore(sc.getIdScore());
            if (sr.isEmpty()){
                return scoreRepository.save(sc);
            }else{
                return sc;
            }
        }
    }
    public Score update(Score sc) {
        if (sc.getIdScore() != null) {
            Optional<Score> so = scoreRepository.getScore(sc.getIdScore());
            if (!so.isEmpty()) {
                if (sc.getScoreVal() != null) {
                    so.get().setScoreVal(sc.getScoreVal());
                }
                if (sc.getMesScore() != null) {
                    so.get().setMesScore(sc.getMesScore());
                }
                if (sc.getIdReservation() != null) {
                    so.get().setIdReservation(sc.getIdReservation());
                }

                scoreRepository.save(so.get());
                return so.get();
            }else {
                return sc;
            }
        }else {
            return sc;
        }
    }

    public boolean delete(int idScore){
        boolean flag=false;
        Optional<Score>sc= scoreRepository.getScore(idScore);
        if(sc.isPresent()){
            scoreRepository.delete(sc.get());
            flag=true;
        }
        return flag;
    }
}
