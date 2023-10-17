package dtos;

import models.EvaluateRequestStatus;
import models.Request;

import java.util.List;

public class SolveResponseDto {

     public Request req;

     public Request getReq() {
          return req;
     }

     public void setReq(Request req) {
          this.req = req;
     }

     public List<String> getEvaluatedExpression() {
          return evaluatedExpression;
     }

     public void setEvaluatedExpression(List<String> evaluatedExpression) {
          this.evaluatedExpression = evaluatedExpression;
     }

     public EvaluateRequestStatus getResponseStatus() {
          return responseStatus;
     }

     public void setResponseStatus(EvaluateRequestStatus responseStatus) {
          this.responseStatus = responseStatus;
     }

     public List<String> evaluatedExpression;
     public EvaluateRequestStatus responseStatus;

     public SolveResponseDto(Request req, List<String> evaluatedExpression, EvaluateRequestStatus responseStatus) {
          this.req = req;
          this.evaluatedExpression = evaluatedExpression;
          this.responseStatus = responseStatus;
     }
}
