package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.Order;
import com.lapzone.lapzoneweb.model.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/admin/invoices")
public class AdminInvoiceController {

    @Autowired
    private OrderService orderService;

    // 1. HIỂN THỊ GIAO DIỆN HÓA ĐƠN VÀ THỐNG KÊ
    @GetMapping
    public String listInvoices(Model model) {
        model.addAttribute("invoices", orderService.getValidInvoices());
        model.addAttribute("monthlyRevenue", orderService.getCurrentMonthRevenue());
        return "admin/invoices"; // Trỏ tới file HTML
    }

    // 2. CHỨC NĂNG XUẤT EXCEL CỰC ĐỈNH
    @GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // Cấu hình file trả về là Excel (Dùng chuẩn Jakarta)
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=DoanhThu_Lapzone_" + new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()) + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Order> invoices = orderService.getValidInvoices();

        // Tạo file Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách Hóa đơn");

        // Tạo dòng Tiêu đề (Header)
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Mã HĐ", "Khách hàng", "Số điện thoại", "Ngày tạo", "Trạng thái", "Tổng tiền"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Đổ dữ liệu vào các dòng
        int rowIdx = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Order order : invoices) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue("#" + order.getId());
            row.createCell(1).setCellValue(order.getCustomerName());
            row.createCell(2).setCellValue(order.getCustomerPhone());
            row.createCell(3).setCellValue(sdf.format(order.getOrderDate()));
            row.createCell(4).setCellValue(order.getStatus());
            row.createCell(5).setCellValue(order.getTotalAmount());
        }

        // Căn chỉnh độ rộng cột tự động
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi ra luồng phản hồi
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    // 3. XỬ LÝ CẬP NHẬT HÓA ĐƠN
    @PostMapping("/update")
    public String updateInvoice(
            @RequestParam("invoiceId") Long invoiceId,
            @RequestParam("customerName") String customerName,
            @RequestParam("customerPhone") String customerPhone,
            @RequestParam("address") String address,
            @RequestParam("totalAmount") Double totalAmount,
            @RequestParam("status") String status,
            org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
        try {
            orderService.updateInvoiceDetails(invoiceId, customerName, customerPhone, address, totalAmount, status);
            ra.addFlashAttribute("success", "Cập nhật hóa đơn #" + invoiceId + " thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Lỗi khi cập nhật: " + e.getMessage());
        }
        return "redirect:/admin/invoices";
    }

    // 4. XỬ LÝ XÓA HÓA ĐƠN
    @PostMapping("/delete")
    public String deleteInvoice(@RequestParam("invoiceId") Long invoiceId, org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
        try {
            orderService.deleteInvoiceAndDetails(invoiceId);
            ra.addFlashAttribute("success", "Đã xóa vĩnh viễn hóa đơn #" + invoiceId + " cùng các chứng từ liên quan!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Lỗi khi xóa hóa đơn: " + e.getMessage());
        }
        return "redirect:/admin/invoices";
    }
}