//package th.co.erp.sme.service;
//
//
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//import th.co.erp.sme.base.BaseResponse;
//import th.co.erp.sme.model.entity.UserEntity;
//import th.co.erp.sme.model.request.UserReportRequest;
//import th.co.erp.sme.repository.UserRepository;
//import th.co.erp.sme.util.Constant;
//
//import java.io.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class GenerateReportService {
//
//    private final UserRepository userRepository;
//
//
//    @SneakyThrows
//    public BaseResponse execute(HttpServletResponse response, UserReportRequest userReportRequest) {
//        LocalDate from;
//        LocalDate to;
//
//        if (Objects.isNull(userReportRequest) || Objects.isNull(userReportRequest.getFrom())) {
//            from = LocalDate.now().minusMonths(1);
//            to = LocalDate.now();
//        } else {
//            from = userReportRequest.getFrom();
//            to = userReportRequest.getTo();
//        }
//
//
//        response.setContentType("application/pdf");
//        String filename = "USER_REPORT_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf";
//        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//        List<UserEntity> userEntityList = userRepository.findAllByIsDeletedAndCreateDateBetween(Constant.FLAG_N, from, to);
//
////        File file = ResourceUtils.getFile("classpath:jasper/UserReport.jrxml");
////        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userEntityList);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Tar");
//
////        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//        File file = ResourceUtils.getFile("classpath:jasper/UserReport.jasper");
//        InputStream jasperStream = new FileInputStream(file);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
//
//        OutputStream outputStream = response.getOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//
//        printStream.write(JasperExportManager.exportReportToPdf(jasperPrint));
//        printStream.flush();
//        printStream.flush();
//        printStream.close();
//        outputStream.flush();
//        outputStream.close();
//        return null;
//    }
//}
