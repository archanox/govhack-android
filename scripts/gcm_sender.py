#!/usr/bin/env python

from gcm import GCM

API_KEY = "AIzaSyCtSZNZzRz0TNkCaKYdVdMWk2Msj5Uni3s"

gcm = GCM(API_KEY)
data = {'param1': 'value1', 'param2': 'value2'}

# Plaintext request
#reg_id = 'APA91bF_IrlzTFRI9ZidgHl3-fDLYBVLLa3IE6n4egtqXwnfkNusdrmCbmiWPN8tiR4goAnsJ5XlSOoqAPPX-7qad17mp65b8vDyCbMtWr_1gDpUanuGnIJkiTkQ-AThHj2tyRcbJ4duSySbptT0NJfT4mT9Gsft1hovHB3tONnMElsEHzF1K6Y'
reg_id = 'APA91bEvbIGnrzFHOFIZerpyBkWpsKqtV1IhK4bCD4bToMhG9vnGjU4HdZHZ7kN6Hrun-Sfl7trE7M3fKl3EQct-B6CBVs1SqBd18SmOc-bRZvFfMLAR0iGZ_Dgz-EBxXzhv-tqb3kN3T71SxhkhgkJWViylFhqCuHHXf576ERJgRNsY74pBZcM'
gcm.plaintext_request(registration_id=reg_id, data=data)

