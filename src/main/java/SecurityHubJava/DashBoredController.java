package SecurityHubJava;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

public class DashBoredController {
    @FXML
    private TableView<Event> tableEvents;
    @FXML
    private TableColumn<Event, String> colType;
    @FXML
    private TableColumn<Event, String> colLevel;
    @FXML
    private TableColumn<Event, String> colSource;
    @FXML
    private TableColumn<Event, String> coltime;
    @FXML
    private ComboBox<String> comboLevelFilter;
    @FXML
    private Label labelStats;
    @SuppressWarnings("FieldMayBeFinal")
    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Use setCellValueFactory to provide cell values (ObservableValue), not setCellFactory
        colType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        colLevel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLevel()));
        colSource.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSource()));
        coltime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));

        comboLevelFilter.getItems().addAll("ALL", "High", "Medium", "Low");
        comboLevelFilter.setValue("ALL");
        loadMockData();
        updateTable();
    }

    public void loadMockData() {
        allEvents.clear();
        allEvents.addAll(
            new Event("Motion Detected", "High", "C++", "12:30:10"),
            new Event("Login Attempt", "Medium", "Python", "12:31:05"),
            new Event("Normal Ping", "Low", "Java", "12:32:20")
        );
    }

    private void updateTable() {
        String filter = comboLevelFilter.getValue();
        ObservableList<Event> filtered = FXCollections.observableArrayList();
        for (Event e : allEvents) {
            if (filter.equalsIgnoreCase("ALL") || e.getLevel().equalsIgnoreCase(filter)) {
                filtered.add(e);
            }
        }
        tableEvents.setItems(filtered);
        updateStats(filtered);
    }

    private void updateStats(ObservableList<Event>  events) {
        int total = events.size();
        int high = (int) events.stream().filter(e -> e.getLevel().equalsIgnoreCase("High")).count();
        int medium = (int) events.stream().filter(e -> e.getLevel().equalsIgnoreCase("Medium")).count();
        int low = (int) events.stream().filter(e -> e.getLevel().equalsIgnoreCase("Low")).count();
        labelStats.setText("Total " + total + " (High: " + high + ", Medium: " + medium + ", Low: " + low + ")");
    }

    @FXML
    private void onRefreshClicked() {
        loadMockData();
        updateTable();
    }
      @FXML
    public void onClearClicked(){
        tableEvents.getItems().clear();
        labelStats.setText(" Total: 0 (High: 0 , Medium : 0,  Low: 0 )");
    }
      @FXML
      public void onFilterChanged(){
        updateTable();
      }

}
