<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Reports Application</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<style>
    body {
        background: #f2f4f8;
        font-family: 'Segoe UI', sans-serif;
    }
    .page-title {
        font-weight: 600;
        color: #2c3e50;
    }
    .card-custom {
        border-radius: 12px;
        padding: 25px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.08);
    }
    .table thead {
        background: #0d6efd;
        color: #fff;
    }
    .btn-custom {
        border-radius: 6px;
        padding: 6px 20px;
    }
    .export-links a {
        margin-right: 15px;
        font-weight: 500;
    }
</style>

</head>
<body>

<div class="container py-4">

    <h2 class="text-center page-title pb-3">📊 Reports Application</h2>

    <!-- Filter Card -->
    <div class="card card-custom mb-4">
        <h5 class="mb-3">Search Filters</h5>

        <form:form action="/searchData" method="POST" modelAttribute="search">

            <div class="row g-3">

                <!-- Plan Name -->
                <div class="col-md-4">
                    <label class="form-label">Plan Name</label>
                    <form:select path="planName" class="form-select">
                        <form:option value="">-- Select --</form:option>
                        <form:options items="${names}" />
                    </form:select>
                </div>

                <!-- Plan Status -->
                <div class="col-md-4">
                    <label class="form-label">Plan Status</label>
                    <form:select path="planStatus" class="form-select">
                        <form:option value="">-- Select --</form:option>
                        <form:options items="${status}" />
                    </form:select>
                </div>

                <!-- Gender -->
                <div class="col-md-4">
                    <label class="form-label">Gender</label>
                    <form:select path="gender" class="form-select">
                        <form:option value="">-- Select --</form:option>
                        <form:option value="Male">Male</form:option>
                        <form:option value="Fe-Male">Fe-Male</form:option>
                    </form:select>
                </div>

                <!-- Start Date -->
                <div class="col-md-4">
                    <label class="form-label">Start Date</label>
                    <form:input type="date" path="startDate" class="form-control" />
                </div>

                <!-- End Date -->
                <div class="col-md-4">
                    <label class="form-label">End Date</label>
                    <form:input type="date" path="endDate" class="form-control" />
                </div>

            </div>

            <div class="text-end mt-4">
                <a href="/" class="btn btn-secondary btn-custom">Reset</a>
                <button type="submit" class="btn btn-primary btn-custom">Search</button>
            </div>

        </form:form>

    </div>

    <!-- Results Table -->
    <div class="card card-custom">
        <h5 class="mb-3">Search Results</h5>

        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Holder Name</th>
                    <th>Gender</th>
                    <th>Plan Name</th>
                    <th>Plan Status</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Benefit Amt</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${plans}" var="plan" varStatus="index">
                    <tr>
                        <td>${index.count}</td>
                        <td>${plan.citizenName}</td>
                        <td>${plan.gender}</td>
                        <td>${plan.planName}</td>
                        <td>${plan.planStatus}</td>
                        <td>${plan.planStartDate}</td>
                        <td>${plan.planEndDate}</td>
                        <td>${plan.benefitAmt}</td>
                    </tr>
                </c:forEach>

                <c:if test="${empty plans}">
                    <tr>
                        <td colspan="8" class="text-center text-muted">
                            No Records Found
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>

        <div class="export-links mt-3">
            <strong>Export:</strong>
            <a href="/excel">Excel</a>
            <a href="/pdf">PDF</a>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
