package services;

import java.util.List;
import model.Equipment;
import repository.EquipmentRepository;

public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService() {
        this.equipmentRepository = new EquipmentRepository();
    }

    public boolean saveEquipment(Equipment equipment) {
        if (equipment == null) {
            System.out.println("Erro: Equipamento inválido!");
            return false;
        }
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipments() {
        List<Equipment> equipments = equipmentRepository.getAllEquipments();
        if (equipments == null || equipments.isEmpty()) {
            System.out.println("Nenhum equipamento encontrado.");
        }
        return equipments;
    }

    public Equipment findEquipmentById(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Erro: ID inválido!");
            return null;
        }
        Equipment equipment = equipmentRepository.findById(id);
        if (equipment == null) {
            System.out.println("Equipamento não encontrado para o ID: " + id);
        }
        return equipment;
    }

    public boolean updateEquipment(Long id, Equipment equipment) {
        if (id == null || id <= 0 || equipment == null) {
            System.out.println("Erro: Dados inválidos para atualização!");
            return false;
        }
        boolean updated = equipmentRepository.update(id, equipment);
        if (!updated) {
            System.out.println("Falha ao atualizar o equipamento com ID: " + id);
        }
        return updated;
    }

    public void deleteEquipment(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Erro: ID inválido para exclusão!");
            return;
        }
        equipmentRepository.delete(id);
        System.out.println("Equipamento removido com sucesso para o ID: " + id);
    }

    public void closeService() {
        equipmentRepository.close();
        System.out.println("Serviço de equipamento fechado.");
    }
}
