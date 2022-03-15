function searchTable() {
    return '<form id="search-form">' +
        '        <table class="table" style="border: #FFFFFF">' +
        '            <tbody>' +
        '                <td>' +
        '                    <label><input class="form-control" placeholder="First name" type="text" name="firstName" id="firstName"/></label>' +
        '                </td>' +
        '                <td>' +
        '                    <label><input class="form-control" placeholder="Last name" type="text" name="lastName" id="lastName"/></label>' +
        '                </td>' +
        '                <td>' +
        '                    <label><input class="form-control" placeholder="Email" type="text" name="email" id="email"/></label>' +
        '                </td>' +
        '                <td>' +
        '                    <label>' +
        '                        <select class="form-select" name="role" id="role" style="width: 242px">' +
        '                           <option value="">&nbsp;&nbsp;&nbsp;&nbsp;' + Role + '</option>' +
        '                           <option value=' + student + '>&nbsp;&nbsp;&nbsp;&nbsp;' + Student + '</option>' +
        '                           <option value=' + teacher + '>&nbsp;&nbsp;&nbsp;&nbsp;' + Teacher + '</option>' +
        '                        </select>' +
        '                    </label>' +
        '                </td>' +
        '                <td>' +
        '                    <input class="btn btn-common" type="submit" id="search" value="Search" style="width: 150px; margin-top: 0"/>' +
        '                </td>' +
        '            </tbody>' +
        '        </table>' +
        '    </form>';
}

function educationalUsersTable(data) {
    let result;
    if (isEmpty(data)) {
        result =
            '<p class="error" style="color: #ef476f">' +
            '   <img src="/assets/image/warning.png" alt="Warning" width="20" height="20">' + '&nbsp;' + message_noEducationalUser +
            '</p>';
    }
    else {
        let rows = '';
        for (let i = 0; i < data.length; i++) {
            rows +=
                '<tr>' +
                '   <td>' + data[i].firstName + '</td>' +
                '   <td>' + data[i].lastName + '</td>' +
                '   <td>' + data[i].email + '</td>' +
                '   <td>' + data[i].roles[0].name + '</td>' +
                '   <td>' + data[i].emailVerification + '</td>' +
                '   <td>' + data[i].adminVerification + '</td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-success" style="width: 100px">' +
                '           <a href=/educational-user/confirm/' + data[i].id + '>' + button_confirm + '</a>' +
                '       </button>' +
                '   </td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-success" style="width: 100px">' +
                '           <a href=/educational-user/' + data[i].id + '/add-to-course>' + button_addToCourse + '' +
                '       </button>' +
                '   </td>' +
                '</tr>';
        }
        result =
            '<table class = "table">' +
            '   <thead>' +
            '       <tr>' +
            '           <th>' + educationalUser_firstName + '</th>' +
            '           <th>' + educationalUser_lastName + '</th>' +
            '           <th>' + educationalUser_email + '</th>' +
            '           <th>' + educationalUser_role + '</th>' +
            '           <th>' + educationalUser_emailVerification + '</th>' +
            '           <th>' + educationalUser_adminVerification + '</th>' +
            '           <th>' + educationalUser_confirm + '</th>' +
            '           <th>' + educationalUser_addToCourse + '</th>' +
            '       </tr>' +
            '   </thead>' +
            '   <tbody>' +
            rows +
            '   </tbody>' +
            '</table>'
    }
    return result;
}

function coursesTableAdminDashboard(data) {
    let result;
    if (isEmpty(data)) {
        result =
            '<p class="error" style="color: #ef476f">' +
            '   <img src="/assets/image/warning.png" alt="Warning" width="20" height="20">' + '&nbsp;' + message_noEducationalUser +
            '</p>';
    }
    else {
        let rows = '';
        for (let i = 0; i < data.length; i++) {
            rows +=
                '<tr>' +
                '   <td>' + data[i].title + '</td>' +
                '   <td>' + data[i].category + '</td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-common" id="course-educational-users" onclick="getEducationalUsers(' + data[i].id + ')">' +
                '           ' + button_educationalUsers +
                '       </button>' +
                '   </td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-danger">' +
                '           <a href=/courses/delete/' + data[i].id + '>' + button_delete + '' +
                '       </button>' +
                '   </td>' +
                '</tr>';
        }
        result =
            '<table class = "table">' +
            '   <thead>' +
            '       <tr>' +
            '           <th>' + course_title + '</th>' +
            '           <th>' + course_category + '</th>' +
            '           <th>' + course_educationalUsers + '</th>' +
            '           <th>' + course_delete + '</th>' +
            '       </tr>' +
            '   </thead>' +
            '   <tbody>' +
            rows +
            '   </tbody>' +
            '</table>'
    }
    return result;
}

function coursesTableTeacherDashboard(data) {
    let result;
    if (isEmpty(data)) {
        result =
            '<p class="error" style="color: #ef476f">' +
            '   <img src="/assets/image/warning.png" alt="Warning" width="20" height="20">' + '&nbsp;' + message_noEducationalUser +
            '</p>';
    }
    else {
        let rows = '';
        for (let i = 0; i < data.length; i++) {
            rows +=
                '<tr>' +
                '   <td>' + data[i].title + '</td>' +
                '   <td>' + data[i].category + '</td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-common" id="course-exams" onclick="getExams(' + data[i].id + ')">' +
                '           ' + button_exams +
                '       </button>' +
                '   </td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-success">' +
                '           <a href=/courses/' + data[i].id + '/add-exam>' + button_addExam + '' +
                '       </button>' +
                '   </td>' +
                '</tr>';
        }
        result =
            '<table class = "table">' +
            '   <thead>' +
            '       <tr>' +
            '           <th>' + course_title + '</th>' +
            '           <th>' + course_category + '</th>' +
            '           <th>' + course_exams + '</th>' +
            '           <th>' + course_addExam + '</th>' +
            '       </tr>' +
            '   </thead>' +
            '   <tbody>' +
            rows +
            '   </tbody>' +
            '</table>'
    }
    return result;
}

function questionTableTeacherDashboard(data) {
    let result;
    if (isEmpty(data)) {
        result =
            '<p class="error" style="color: #ef476f">' +
            '   <img src="/assets/image/warning.png" alt="Warning" width="20" height="20">' + '&nbsp;' + message_noEducationalUser +
            '</p>';
    }
    else {
        let rows = '';
        for (let i = 1; i < data.length; i++) {
            rows +=
                '<tr>' +
                '   <td>' + data[i].question + '</td>';
            for (let j = 0; j < data[i].answers.length; j++) {
                rows +=
                    '   <td>' + data[i].answers[j] + '</td>';
            }

            rows +=
                '   <td>' + data[i].correctAnswer + '</td>' +
                '</tr>';
        }
        result =
            '<table class = "table">' +
            '   <thead>' +
            '       <tr>' +
            '           <th>' + question_question + '</th>' +
            '           <th>' + question_answer1 + '</th>' +
            '           <th>' + question_answer2 + '</th>' +
            '           <th>' + question_answer3 + '</th>' +
            '           <th>' + question_answer4 + '</th>' +
            '           <th>' + question_correctAnswer + '</th>' +
            '       </tr>' +
            '   </thead>' +
            '   <tbody>' +
            rows +
            '   </tbody>' +
            '</table>'
    }
    return result;
}

function coursesTableStudentDashboard(data) {
    let result;
    if (isEmpty(data)) {
        result =
            '<p class="error" style="color: #ef476f">' +
            '   <img src="/assets/image/warning.png" alt="Warning" width="20" height="20">' + '&nbsp;' + message_noEducationalUser +
            '</p>';
    }
    else {
        let rows = '';
        for (let i = 0; i < data.length; i++) {
            rows +=
                '<tr>' +
                '   <td>' + data[i].title + '</td>' +
                '   <td>' + data[i].category + '</td>' +
                '   <td>' +
                '       <button type="button" class="btn btn-common" id="course-exams" onclick="getExams(' + data[i].id + ')">' +
                '           ' + button_exams +
                '       </button>' +
                '   </td>' +
                '</tr>';
        }
        result =
            '<table class = "table">' +
            '   <thead>' +
            '       <tr>' +
            '           <th>' + course_title + '</th>' +
            '           <th>' + course_category + '</th>' +
            '           <th>' + course_exams + '</th>' +
            '       </tr>' +
            '   </thead>' +
            '   <tbody>' +
            rows +
            '   </tbody>' +
            '</table>'
    }
    return result;
}
