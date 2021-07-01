class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer,NumInfo> map=new HashMap();
        // 每个数的个数和长度
        // 求最大度
        // 数字 计数字 首位
        // 怎么处理最大度数？
        int maxDu=-1;
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                NumInfo numInfo=new NumInfo();
                numInfo.value=nums[i];
                numInfo.start=i;
                numInfo.end=i;
                numInfo.count=1;
                map.put(nums[i],numInfo);
                maxDu=Math.max(maxDu,numInfo.count);
            }else{
                NumInfo numInfo=map.get(nums[i]);
                numInfo.count+=1;
                numInfo.end=i;
                maxDu=Math.max(maxDu,numInfo.count);
            }
        }
        // 首先是度，然后是最短子数组
        int minLength=50000;
        for(Integer i:map.keySet()){
            if(map.get(i).count==maxDu){
                minLength=Math.min(minLength,map.get(i).end-map.get(i).start+1);
            }
        }
        return minLength;
        
       

        


    }
    static class NumInfo{
            int value;
            int count=0;
            int start;
            int end;
        
        }
 
}