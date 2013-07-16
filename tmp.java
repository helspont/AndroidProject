for (int out = 0; out < counter_out; out++) {
                        int tmpMax = Integer.MIN_VALUE;
                        int tmpMin = Integer.MAX_VALUE;
                      
                        for (int in = 0; in < counter_in; in++) {
                            if(graph[edges_in[in]][2]!=graph[edges_out[out]][1]){
                                int tmpValue = maxvaluedge[c];
                                if(graph[edges_in[in]][0]+tmpValue > tmpMax){
                                    tmpMax = graph[edges_in[in]][0]+tmpValue;
                                }
                                if(graph[edges_in[in]][0]+tmpValue < tmpMin){
                                    tmpMin = graph[edges_in[in]][0]+tmpValue;
                                }
                            }else{
                                int tmpValue = maxvaluedge[c];
                                if(graph[edges_in[in]][0]+tmpValue > tmpMax){
                                    tmpMax = graph[edges_in[in]][0]+tmpValue;
                                }
                                if(graph[edges_in[in]][0] < tmpMin){
                                    tmpMin = graph[edges_in[in]][0];
                                }
                            }
                            
                            graph[edges_out[out]][0]+=tmpMin;
                            graph[edges_out[out]][4]+=tmpMax;
                            
                        }
                        
                    }