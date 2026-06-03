# 🛠️ EquipmentRentalSystem

Projekt akademicki zrealizowany w ramach zaliczenia przedmiotu **Programowanie Obiektowe** (Java). Jest to w pełni funkcjonalna aplikacja konsolowa (CLI) służąca do kompleksowego zarządzania procesami i zasobami wypożyczalni sprzętu.

## 🚀 Główne Funkcjonalności
* **Zarządzanie użytkownikami:** Dodawanie klientów z automatyczną weryfikacją unikalności oraz poprawności danych (format numeru PESEL oraz numeru telefonu) przy użyciu wyrażeń regularnych (Regex).
* **Zarządzanie asortymentem:** Obsługa dwóch kategorii sprzętu: ciężkiego (wymagającego od klienta posiadania prawa jazdy) oraz lekkiego.
* **Proces wypożyczeń i zwrotów:** Zaawansowany mechanizm sprawdzający dostępność zasobów, uprawnienia użytkownika, poprawność dat oraz automatycznie naliczający opłaty na podstawie czasu trwania wypożyczenia.
* **Trwały zapis danych (File I/O):** Aplikacja symuluje relacyjną bazę danych poprzez odczyt oraz zapis informacji (konta użytkowników, asortyment, historia transakcji) do plików tekstowych `.txt`.
* **Moduł raportowania:** Możliwość szybkiego sprawdzenia aktywnego sprzętu przypisanego do danego klienta oraz wgląd w pełną historię wypożyczeń.

## 💻 Wykorzystane Technologie i Wzorce (OOP)
* **Język:** Java (Core)
* **Kontrola wersji:** Git / GitHub
* **Paradygmaty Programowania Obiektowego:** * **Dziedziczenie i Polimorfizm:** Wykorzystanie klasy abstrakcyjnej `Equipment` jako bazy dla klas `HeavyEquipment` oraz `LightEquipment`. Nadpisanie metody `canBeRentedBy(User)` pozwala na dynamiczne sprawdzanie logiki uprawnień zależnie od typu obiektu.
  * **Kompozycja:** Klasa `Rental` agreguje obiekty klas `User` oraz `Equipment`, wiążąc je z parametrami czasowymi i kosztowymi.
  * **Hermetyzacja:** Pełne ukrycie pól klas i kontrolowany dostęp do nich poprzez metody dostępowe (gettery/settery).
* **Obsługa błędów (Custom Exceptions):** Implementacja własnych klas wyjątków (np. `EquipmentUnavailableException`, `NoDrivingLicenseException`, `IllegalDateException`) gwarantuje odporność aplikacji na błędną logikę biznesową.

## ⚙️ Jak uruchomić projekt lokalnie

1. Sklonuj repozytorium na swój komputer:
   ```bash
   git clone [https://github.com/KacperG06/EquipmentRentalSystem.git](https://github.com/KacperG06/EquipmentRentalSystem.git)