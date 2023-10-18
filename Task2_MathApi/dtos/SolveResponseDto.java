package dtos;

import models.EvaluateRequestStatus;
import models.Request;

import java.util.List;

public class SolveResponseDto {

     public Request req;
     public List<String> answers;

     public SolveResponseDto(Request req, List<String> answers, EvaluateRequestStatus responseStatus) {
          this.req = req;
          this.answers = answers;
          this.responseStatus = responseStatus;
     }

     public List<String> getAnswers() {
          return answers;
     }

     public void setAnswers(List<String> answers) {
          this.answers = answers;
     }

     public EvaluateRequestStatus responseStatus;

     public Request getReq() {
          return req;
     }

     public void setReq(Request req) {
          this.req = req;
     }

     public EvaluateRequestStatus getResponseStatus() {
          return responseStatus;
     }

     public void setResponseStatus(EvaluateRequestStatus responseStatus) {
          this.responseStatus = responseStatus;
     }
}
