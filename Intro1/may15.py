def dictinv(D):
    ans = {}
    K = D.keys()
    for akey in K:
        if ans.has_key(D[akey]):
            ans[D[akey]].append(akey)
        else:
            ans[D[akey]] = [akey]
    return ans


                       
