package joufuniversitygym;

// استيراد المكتبات المطلوبة للرسوميات والحركات
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * الكلاس الرئيسي لتطبيق نظام إدارة اشتراكات النادي الرياضي لجامعة الجوف
 * يوفر واجهة رسومية لإضافة وتحديث وحذف وعرض بيانات الطلاب المشتركين
 */
public class JoufUniversityGYM extends Application {

    /**
     * نقطة البداية الرئيسية للتطبيق - تقوم بإنشاء الواجهة الرسومية
     * @param primaryStage النافذة الرئيسية للتطبيق
     */
    @Override
    public void start(Stage primaryStage) {
        
        // إنشاء صورة الشعار
        ImageView logo = new ImageView();
        try {
            // تحميل صورة شعار الجامعة من الإنترنت
            Image logoImage = new Image("https://saudipedia.com/en/saudipediaen/uploads/images/2024/05/29/thumbs/400x400/67089.jpg");
            logo.setImage(logoImage);
            logo.setFitWidth(100);  // عرض الصورة
            logo.setFitHeight(100); // ارتفاع الصورة
            logo.setPreserveRatio(true); // الحفاظ على نسبة أبعاد الصورة
            logo.setOpacity(0); // جعل الصورة شفافة في البداية للحركة
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid image URL: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + e.getMessage());
        }
        
        // ==== إنشاء رأس الصفحة (Header) ====
        // عنوان رئيسي
        Label titleLabel = new Label("جامعة الجوف - النادي الرياضي");
        titleLabel.getStyleClass().add("header-title");
        titleLabel.setOpacity(0); // شفاف في البداية
        
        // عنوان فرعي
        Label subTitleLabel = new Label("نظام إدارة اشتراكات الطلاب");
        subTitleLabel.getStyleClass().add("header-subtitle");
        subTitleLabel.setOpacity(0); // شفاف في البداية
        
        // صندوق عمودي لوضع العناوين
        VBox headerText = new VBox(5, titleLabel, subTitleLabel);
        headerText.setAlignment(Pos.CENTER_RIGHT);
        
        // صندوق أفقي للرأس يحتوي على الشعار والعناوين
        HBox header = new HBox(20, logo, headerText);
        header.getStyleClass().add("header-panel");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(20));
        HBox.setHgrow(headerText, Priority.ALWAYS);
        
        // تطبيق الحركات الانتقالية (Animations)
        animateFadeIn(titleLabel, 0.5);
        animateFadeIn(subTitleLabel, 1.0);
        animateFadeIn(logo, 0.3);

        // ==== إنشاء نموذج الإدخال (Form) ====
        GridPane grid = new GridPane();
        grid.setHgap(20);  // المسافة الأفقية بين الأعمدة
        grid.setVgap(15);  // المسافة العمودية بين الصفوف
        grid.setAlignment(Pos.CENTER);

        // حقل الرقم الجامعي
        Label idLabel = new Label("الرقم الجامعي:");
        TextField idField = new TextField();
        idField.setPromptText("مثال: 441200...");
        grid.add(idLabel, 1, 0);    // العمود 1، الصف 0
        grid.add(idField, 0, 0);    // العمود 0، الصف 0

        // حقل الاسم الكامل
        Label nameLabel = new Label("الاسم الكامل:");
        TextField nameField = new TextField();
        nameField.setPromptText("الاسم الثلاثي");
        // التحقق من صحة الإدخال - السماح فقط بالأحرف العربية والإنجليزية والمسافات
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\u0600-\\u06FFa-zA-Z\\s]*")) {
                nameField.setText(oldValue);
            }
        });
        grid.add(nameLabel, 1, 1);
        grid.add(nameField, 0, 1);

        // حقل السنة الدراسية
        Label yearLabel = new Label("السنة الدراسية:");
        TextField yearField = new TextField();
        yearField.setPromptText("مثال: 2025");
        grid.add(yearLabel, 1, 2);
        grid.add(yearField, 0, 2);

        // حقل التخصص الأكاديمي
        Label majorLabel = new Label("التخصص الأكاديمي:");
        TextField majorField = new TextField();
        majorField.setPromptText("مثال: علوم حاسب");
        // التحقق من صحة الإدخال - السماح فقط بالأحرف العربية والإنجليزية والمسافات
        majorField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\u0600-\\u06FFa-zA-Z\\s]*")) {
                majorField.setText(oldValue);
            }
        });
        grid.add(majorLabel, 1, 3);
        grid.add(majorField, 0, 3);

        // قائمة اختيار النادي
        Label clubLabel = new Label("النادي المطلوب:");
        ChoiceBox<String> clubChoice = new ChoiceBox<>();
        clubChoice.getItems().addAll("كرة سلة 🏀", "كرة قدم ⚽", "صالة أثقال 🏋️", "ألعاب إلكترونية 🎮");
        clubChoice.setValue("اختر النادي...");
        clubChoice.setMaxWidth(Double.MAX_VALUE);
        grid.add(clubLabel, 1, 4);
        grid.add(clubChoice, 0, 4);

        // صندوق يحتوي على النموذج مع التنسيق
        VBox formContainer = new VBox(grid);
        formContainer.getStyleClass().add("form-card");
        formContainer.setMaxWidth(600);

        // ==== إنشاء الأزرار (Buttons) ====
        // زر إضافة طالب جديد
        Button addBtn = new Button("💾 تسجيل جديد");
        addBtn.getStyleClass().addAll("button", "btn-add");

        // زر تحديث بيانات طالب موجود
        Button updateBtn = new Button("✏️ تحديث بيانات");
        updateBtn.getStyleClass().addAll("button", "btn-update");

        // زر حذف طالب
        Button deleteBtn = new Button("🗑️ حذف طالب");
        deleteBtn.getStyleClass().addAll("button", "btn-delete");

        // زر عرض جميع الطلاب
        Button viewUsersBtn = new Button("📋 عرض الكل");
        viewUsersBtn.getStyleClass().addAll("button", "btn-view");

        // زر الخروج من البرنامج
        Button exitBtn = new Button("🚪 خروج");
        exitBtn.getStyleClass().addAll("button", "btn-exit");
        exitBtn.setOnAction(e -> primaryStage.close());

        // صندوق أفقي للأزرار
        HBox actionBtns = new HBox(15, addBtn, updateBtn, deleteBtn, viewUsersBtn);
        actionBtns.setAlignment(Pos.CENTER);

        // تسمية لعرض رسائل النجاح أو الخطأ
        Label messageLabel = new Label();
        messageLabel.setMaxWidth(600);
        messageLabel.setAlignment(Pos.CENTER);

        // ==== إضافة وظائف الأزرار (Button Actions) ====
        
        // وظيفة زر الإضافة - تسجيل طالب جديد
        addBtn.setOnAction(e -> {
            // التحقق من صحة البيانات المدخلة
            if (validateInputs(idField, nameField, yearField, majorField, clubChoice, messageLabel)) {
                try {
                    // حفظ بيانات الطالب في الملف
                    FileManager.saveStudent(
                        idField.getText().trim(),
                        nameField.getText().trim(),
                        yearField.getText().trim(),
                        majorField.getText().trim(),
                        clubChoice.getValue()
                    );
                    showSuccess(messageLabel, "تم تسجيل الطالب بنجاح!");
                    clearFields(idField, nameField, yearField, majorField, clubChoice);
                } catch (IOException ex) {
                    showError(messageLabel, "خطأ: " + ex.getMessage());
                }
            }
        });

        // وظيفة زر التحديث - تعديل بيانات طالب موجود
        updateBtn.setOnAction(e -> {
            String id = idField.getText().trim();
            // التأكد من إدخال الرقم الجامعي
            if (id.isEmpty()) {
                showError(messageLabel, "أدخل الرقم الجامعي للتحديث");
                return;
            }
            // التحقق من صحة باقي البيانات
            if (validateInputs(idField, nameField, yearField, majorField, clubChoice, messageLabel)) {
                try {
                    // تحديث بيانات الطالب في الملف
                    if (FileManager.updateStudent(id, nameField.getText().trim(), 
                        yearField.getText().trim(), majorField.getText().trim(), clubChoice.getValue())) {
                        showSuccess(messageLabel, "تم التحديث بنجاح");
                        clearFields(idField, nameField, yearField, majorField, clubChoice);
                    } else {
                        showError(messageLabel, "رقم جامعي غير موجود");
                    }
                } catch (IOException ex) {
                    showError(messageLabel, "خطأ: " + ex.getMessage());
                }
            }
        });

        // وظيفة زر الحذف - حذف بيانات طالب
        deleteBtn.setOnAction(e -> {
            String id = idField.getText().trim();
            // التأكد من إدخال الرقم الجامعي
            if (id.isEmpty()) {
                showError(messageLabel, "أدخل الرقم الجامعي للحذف");
                return;
            }
            // عرض رسالة تأكيد قبل الحذف
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "هل أنت متأكد من الحذف?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    // حذف الطالب من الملف
                    if (FileManager.deleteStudent(id)) {
                        showSuccess(messageLabel, "تم الحذف بنجاح");
                        clearFields(idField, nameField, yearField, majorField, clubChoice);
                    } else {
                        showError(messageLabel, "رقم جامعي غير موجود");
                    }
                } catch (IOException ex) {
                    showError(messageLabel, "خطأ: " + ex.getMessage());
                }
            }
        });

        // وظيفة زر عرض جميع الطلاب
        viewUsersBtn.setOnAction(e -> {
            try {
                // قراءة وعرض قائمة جميع الطلاب في نافذة جديدة
                showUsersWindow(FileManager.readAllStudents());
            } catch (IOException ex) {
                showError(messageLabel, "خطأ: " + ex.getMessage());
            }
        });

        // ==== تنظيم الواجهة (Layout) ====
        // المحتوى المركزي - يحتوي على النموذج والأزرار
        VBox centerContent = new VBox(20, formContainer, actionBtns, messageLabel, exitBtn);
        centerContent.setPadding(new Insets(30));
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");

        // التخطيط الرئيسي للصفحة
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(header);         // الرأس في الأعلى
        mainLayout.setCenter(centerContent); // المحتوى في الوسط
        mainLayout.getStyleClass().add("border-pane");

        // إنشاء المشهد الرئيسي بأبعاد 800x700
        Scene scene = new Scene(mainLayout, 800, 700);
        
        // تحميل ملف التنسيقات CSS
        URL cssUrl = getClass().getResource("styles-gym.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        // إعداد النافذة الرئيسية وعرضها
        primaryStage.setTitle("Jouf University Gym System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * التحقق من صحة البيانات المدخلة
     * @return true إذا كانت جميع البيانات صحيحة، false خلاف ذلك
     */
    private boolean validateInputs(TextField id, TextField name, TextField year, 
                                   TextField major, ChoiceBox<String> club, Label msg) {
        try {
            // التحقق من أن جميع الحقول ليست فارغة
            if (id.getText().isEmpty() || name.getText().isEmpty() || 
                year.getText().isEmpty() || major.getText().isEmpty() || 
                club.getValue().equals("اختر النادي...")) {
                throw new IllegalArgumentException("جميع الحقول مطلوبة");
            }
            // التحقق من أن الرقم الجامعي يحتوي على أرقام فقط
            if (!id.getText().matches("\\d+")) {
                throw new IllegalArgumentException("الرقم الجامعي يجب أن يكون أرقاماً");
            }
            return true;
        } catch (IllegalArgumentException e) {
            showError(msg, e.getMessage());
            return false;
        } catch (NullPointerException e) {
            showError(msg, "خطأ في البيانات المدخلة");
            return false;
        }
    }

    /**
     * عرض رسالة نجاح باللون الأخضر
     */
    private void showSuccess(Label l, String t) {
        l.setText("✓ " + t);
        l.getStyleClass().setAll("success-message");
    }

    /**
     * عرض رسالة خطأ باللون الأحمر
     */
    private void showError(Label l, String t) {
        l.setText("⚠ " + t);
        l.getStyleClass().setAll("error-message");
    }

    /**
     * تفريغ جميع حقول الإدخال
     */
    private void clearFields(TextField id, TextField name, TextField year, 
                            TextField major, ChoiceBox<String> club) {
        id.clear();
        name.clear();
        year.clear();
        major.clear();
        club.setValue("اختر النادي...");
    }

    /**
     * عرض نافذة منبثقة تحتوي على قائمة جميع الطلاب
     * @param users قائمة بيانات الطلاب
     */
    private void showUsersWindow(List<String> users) {
        // إنشاء نافذة جديدة
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // جعلها نافذة حوارية
        
        // إنشاء منطقة نصية لعرض البيانات
        TextArea ta = new TextArea();
        StringBuilder sb = new StringBuilder();
        // تحويل البيانات من CSV إلى نص مقروء
        for (String u : users) {
            sb.append(u.replace(",", " | ")).append("\n");
        }
        ta.setText(sb.toString());
        
        // عرض النافذة
        window.setScene(new Scene(new StackPane(ta), 500, 400));
        window.show();
    }
    
    /**
     * تطبيق حركة ظهور تدريجي على عنصر Label
     * @param label العنصر المراد تحريكه
     * @param delaySeconds التأخير قبل بدء الحركة بالثواني
     */
    private void animateFadeIn(Label label, double delaySeconds) {
        // حركة الظهور التدريجي (من شفاف إلى واضح)
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), label);
        fade.setFromValue(0.0);  // من شفاف تماماً
        fade.setToValue(1.0);    // إلى واضح تماماً
        fade.setDelay(Duration.seconds(delaySeconds));
        
        // حركة الانزلاق من اليسار
        TranslateTransition slide = new TranslateTransition(Duration.seconds(1.5), label);
        slide.setFromX(-50); // البداية من 50 بكسل لليسار
        slide.setToX(0);     // النهاية في المكان الأصلي
        slide.setDelay(Duration.seconds(delaySeconds));
        
        // تشغيل الحركتين
        fade.play();
        slide.play();
    }
    
    /**
     * تطبيق حركة ظهور تدريجي على صورة ImageView
     * @param imageView الصورة المراد تحريكها
     * @param delaySeconds التأخير قبل بدء الحركة بالثواني
     */
    private void animateFadeIn(ImageView imageView, double delaySeconds) {
        // حركة الظهور التدريجي
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), imageView);
        fade.setFromValue(0.0);  // من شفاف تماماً
        fade.setToValue(1.0);    // إلى واضح تماماً
        fade.setDelay(Duration.seconds(delaySeconds));
        
        // حركة الانزلاق من اليمين (عكس النصوص)
        TranslateTransition slide = new TranslateTransition(Duration.seconds(1.5), imageView);
        slide.setFromX(50);  // البداية من 50 بكسل لليمين
        slide.setToX(0);     // النهاية في المكان الأصلي
        slide.setDelay(Duration.seconds(delaySeconds));
        
        // تشغيل الحركتين
        fade.play();
        slide.play();
    }

    /**
     * النقطة الرئيسية لبدء التطبيق
     */
    public static void main(String[] args) {
        launch(args);
    }
}
