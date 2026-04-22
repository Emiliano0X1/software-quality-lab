package mx.edu.cetys.software_quality_lab.commons;

public record ApiResponse<T> (String info, T response, String error){}
