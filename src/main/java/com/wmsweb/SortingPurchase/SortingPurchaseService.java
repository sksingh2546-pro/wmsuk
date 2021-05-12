
package com.wmsweb.SortingPurchase;

import com.wmsweb.Purchase.Purchase;
import com.wmsweb.Purchase.PurchaseRepository;
import com.wmsweb.bayCapacity.BayCapacity;
import com.wmsweb.bayCapacity.BayCapacityRepository;
import com.wmsweb.production.Production;
import com.wmsweb.production.ProductionRepository;
import com.wmsweb.transport.Transport;
import com.wmsweb.transport.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class SortingPurchaseService {
    @Autowired
    SortingPurchaseRepository sortingPurchaseRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    TransportRepository transportRepository;
    @Autowired
    BayCapacityRepository bayCapacityRepository;

    @PostMapping({"/updateSortingPurchaseStatus"})
    public String updatePurchaseStatus(@RequestBody SortingPurchase sortingPurchase) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateSortingPurchaseStatus = sortingPurchaseRepository.updateSortingPurchaseStatus(sortingPurchase.getId(), sortingPurchase.getStatus());
        if (updateSortingPurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping({"/getSortingPurchase"})
    public Map<String, ArrayList<SortingPurchase>> getSortingPurchase() {
        HashMap<String, ArrayList<SortingPurchase>> hmap = new HashMap<String, ArrayList<SortingPurchase>>();
        ArrayList<SortingPurchase> list = (ArrayList<SortingPurchase>) sortingPurchaseRepository.getSortingPurchase();
        hmap.put("SortingPurchase", list);
        return hmap;
    }

    @GetMapping({"/getOrderProduct"})
    public Map<String, List<PurchaseSortingModel>> getOrderProduct(@RequestParam("order_id") long order_id) {
        Map<String, List<PurchaseSortingModel>> hMap = new HashMap<>();
        List<PurchaseSortingModel> purchaseSortingModelList=new ArrayList<>();

        ArrayList<SortingPurchase> list = (ArrayList<SortingPurchase>) sortingPurchaseRepository.getOrderProduct(order_id);
        list.forEach(sortingPurchase -> {
            List<BayCapacity> getBayList=bayCapacityRepository.getBay(Integer.parseInt(sortingPurchase.getBarcode()));
           if(getBayList.size()>0) {
               PurchaseSortingModel purchaseSortingModel = new PurchaseSortingModel();
               purchaseSortingModel.setBarcode(sortingPurchase.getBarcode());
               purchaseSortingModel.setOrder_id(sortingPurchase.getOrder_id());
               purchaseSortingModel.setBatch_no(sortingPurchase.getBatch_no());
               purchaseSortingModel.setBay(getBayList.get(0).getBay());
               purchaseSortingModel.setExpiry(sortingPurchase.getExpiry());
               purchaseSortingModel.setP_barcode(sortingPurchase.getP_barcode());
               purchaseSortingModel.setPermit_no(sortingPurchase.getPermit_no());
               purchaseSortingModel.setSku(sortingPurchase.getSku());
               purchaseSortingModelList.add(purchaseSortingModel);

           }
        });
        hMap.put("OrderIdProduct", purchaseSortingModelList);
        return hMap;
    }

    @PostMapping({"/updateWithOrderId"})
    public String updateWithOrderId(@RequestParam("order_id") long order_id) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateSortingPurchaseStatus = sortingPurchaseRepository.updateWithOrderId(order_id);
        if (updateSortingPurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }


    @PostMapping("updateSortingQty")
    public String updateSortingQty(@RequestBody SortingPurchase sortingPurchase){
        String message="UnSuccessful";
        List<SortingPurchase> sortingPurchases=sortingPurchaseRepository.getSortingQty(sortingPurchase.getOrder_id(),sortingPurchase.getSku(),
                sortingPurchase.getBatch_no(),sortingPurchase.getBarcode());
        if(sortingPurchases.size()>0) {

            int updateSortingPurchase = sortingPurchaseRepository.updateSortingQty(sortingPurchases.get(0).getQty() + sortingPurchase.getQty(),
                    sortingPurchase.getOrder_id(), sortingPurchase.getSku(), sortingPurchase.getBatch_no(),sortingPurchase.getBarcode());
            if(updateSortingPurchase>0){

                message="Updated";
            }
            List<Purchase>getPurchaseQty=purchaseRepository.getPurchseQuantity(sortingPurchase.getOrder_id());

            if(getPurchaseQty.size()>0){
                if(getPurchaseQty.get(0).getBatch_no()==null){
                    List<Purchase>purchaseList=purchaseRepository.getPurchseSkuQuantity(sortingPurchase.getOrder_id(),sortingPurchase.getSku());
                    if(purchaseList.size()>0) {
                        int qty=purchaseList.get(0).getQty();
                        int newQty=purchaseList.get(0).getQty()+sortingPurchase.getQty();
                        int updatePurchaseQty = purchaseRepository.purchaseQuantity(purchaseList.get(0).getQty() + sortingPurchase.getQty(),
                                sortingPurchase.getOrder_id(), sortingPurchase.getSku());
                               List<Transport> getTransport=transportRepository.getTransportStatus(purchaseList.get(0).getOrder_id());
                               if(getTransport.size()>0) {
                                   String sku=getTransport.get(0).getSku().replace(
                                           sortingPurchase.getSku()+"("+qty+")",
                                           sortingPurchase.getSku()+"("+newQty+")");
                                   int tempQty=Integer.parseInt(getTransport.get(0).getTotal_qty())+sortingPurchase.getQty();

                                   transportRepository.updateSkuAndQty(purchaseList.get(0).getOrder_id(),
                                           String.valueOf(tempQty),sku);
                               }
                        if (updatePurchaseQty > 0) {
                            message = "Updated";
                        }
                    }
                }else {
                    List<Purchase>getPurchaseQtyBatchNo=purchaseRepository.getPurchaseQtyBatchNo(sortingPurchase.getOrder_id(),
                            sortingPurchase.getSku(),sortingPurchase.getBatch_no(),sortingPurchase.getBarcode());
                    if(getPurchaseQtyBatchNo.size()>0) {

                        int qty=getPurchaseQtyBatchNo.get(0).getQty();
                        int newQty=getPurchaseQtyBatchNo.get(0).getQty()+sortingPurchase.getQty();
                        int updatePurchaseQuantity = purchaseRepository.updatePurchaseQty(getPurchaseQty.get(0).getQty() + sortingPurchase.getQty(),
                                sortingPurchase.getOrder_id(), sortingPurchase.getSku(), sortingPurchase.getBatch_no(), sortingPurchase.getBarcode());
                        List<Transport> getTransport=transportRepository.getTransportStatus(getPurchaseQtyBatchNo.get(0).getOrder_id());
                        if(getTransport.size()>0) {
                            String sku=getTransport.get(0).getSku().replace(
                                    sortingPurchase.getSku()+"("+qty+")",
                                    sortingPurchase.getSku()+"("+newQty+")");
                            int tempQty=Integer.parseInt(getTransport.get(0).getTotal_qty())+sortingPurchase.getQty();

                            transportRepository.updateSkuAndQty(getPurchaseQtyBatchNo.get(0).getOrder_id(),
                                    String.valueOf(tempQty),sku);
                        }
                        if (updatePurchaseQuantity > 0) {
                            message = "Updated";
                        }
                    }
                }
            }
        }
        return message;
    }

}
